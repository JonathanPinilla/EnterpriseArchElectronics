package co.sofkau.usecase.getorderbyid;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetOrderByIdUseCase implements Function<String, Mono<Order>> {

    private final OrderGateway orderGateway;

    @Override
    public Mono<Order> apply(String id) {
        return orderGateway.getById(id);
    }

}
