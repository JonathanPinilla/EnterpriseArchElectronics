package co.sofkau.item;

import co.sofkau.item.data.ItemData;
import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ItemMongoRepositoryAdapter implements ItemGateway {

    private final ItemMongoDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Item> getAll() {
        return this.repository.findAll()
                .switchIfEmpty(Flux.empty())
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> getById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Item with id: " + id + " not found")))
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> save(Item item) {
        return this.repository.save(mapper.map(item, ItemData.class))
                .map(itemData -> mapper.map(itemData, Item.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error saving item: " + error)));
    }

    @Override
    public Mono<Item> update(String id, Item item) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Item with id: " + id + " not found")))
                .map(itemData -> {
                    itemData.setName(item.getName());
                    itemData.setPrice(item.getPrice());
                    itemData.setType(item.getType());
                    itemData.setDescription(item.getDescription());
                    itemData.setImage(item.getImage());
                    return mapper.map(item, ItemData.class);
                })
                .flatMap(this.repository::save)
                .map(itemData -> mapper.map(itemData, Item.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error updating item: " + error)));
    }

    @Override
    public Mono<Item> sell(String id, Integer quantity) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Item with id: " + id + " not found")))
                .map(itemData -> {
                    if(itemData.getAvailable() < quantity) {
                        throw new RuntimeException("Not enough items available");
                    }
                    itemData.setAvailable(itemData.getAvailable() - quantity);
                    return mapper.map(itemData, ItemData.class);
                })
                .flatMap(this.repository::save)
                .map(itemData -> mapper.map(itemData, Item.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error selling item: " + error)));
    }

    @Override
    public Mono<Void> softDelete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Item with id: " + id + " not found")))
                .map(itemData -> {
                    itemData.setDeleted(true);
                    return mapper.map(itemData, ItemData.class);
                })
                .flatMap(this.repository::save)
                .then();
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Item with id: " + id + " not found")))
                .flatMap(this.repository::delete);
    }
}
