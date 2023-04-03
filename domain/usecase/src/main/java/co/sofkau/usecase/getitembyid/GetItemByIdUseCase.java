package co.sofkau.usecase.getitembyid;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetItemByIdUseCase implements Function<String, Mono<Item>> {

    private final ItemGateway itemGateway;

    @Override
    public Mono<Item> apply(String id) {
        return itemGateway.getById(id);
    }

}
