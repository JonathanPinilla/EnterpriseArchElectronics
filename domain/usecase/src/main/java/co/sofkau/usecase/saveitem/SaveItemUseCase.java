package co.sofkau.usecase.saveitem;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveItemUseCase implements Function<Item, Mono<Item>> {

    private final ItemGateway itemGateway;

    @Override
    public Mono<Item> apply(Item item) {
        return itemGateway.save(item);
    }

}
