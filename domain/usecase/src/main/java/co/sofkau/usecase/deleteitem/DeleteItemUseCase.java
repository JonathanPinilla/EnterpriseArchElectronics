package co.sofkau.usecase.deleteitem;

import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteItemUseCase implements Function<String, Mono<Void>> {

    private final ItemGateway itemGateway;

    @Override
    public Mono<Void> apply(String id) {
        return itemGateway.delete(id);
    }

}
