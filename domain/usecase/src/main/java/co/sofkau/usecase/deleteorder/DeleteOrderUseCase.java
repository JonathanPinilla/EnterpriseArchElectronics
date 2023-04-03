package co.sofkau.usecase.deleteorder;

import co.sofkau.model.order.gateways.OrderGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteOrderUseCase implements Function<String, Mono<Void>> {

    private final OrderGateway orderGateway;

    @Override
    public Mono<Void> apply(String id) {
        return orderGateway.delete(id);
    }

}
