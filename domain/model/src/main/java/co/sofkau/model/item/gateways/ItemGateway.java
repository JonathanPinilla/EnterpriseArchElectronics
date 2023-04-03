package co.sofkau.model.item.gateways;

import co.sofkau.model.item.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemGateway {

    public Flux<Item> getAll();
    public Mono<Item> getById(String id);
    public Mono<Item> save(Item item);
    public Mono<Void> delete(String id);

}
