package co.sofkau.usecase.getallitems;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllItemsUseCase implements Supplier<Flux<Item>> {

    private final ItemGateway itemGateway;

    @Override
    public Flux<Item> get() {
        return itemGateway.getAll();
    }

}
