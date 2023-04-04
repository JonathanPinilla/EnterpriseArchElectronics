package co.sofkau.usecase.sellitem;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class SellItemUseCase implements BiFunction<String, Integer, Mono<Item>> {

    private final ItemGateway itemGateway;

    @Override
    public Mono<Item> apply(String id, Integer amount) {
        return itemGateway.sell(id, amount);
    }
}
