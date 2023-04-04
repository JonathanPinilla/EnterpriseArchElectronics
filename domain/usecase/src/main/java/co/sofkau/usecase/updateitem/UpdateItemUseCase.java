package co.sofkau.usecase.updateitem;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateItemUseCase implements BiFunction<String, Item, Mono<Item>> {

    private final ItemGateway itemGateway;

    @Override
    public Mono<Item> apply(String id, Item item) {
        return itemGateway.update(id, item);
    }
}
