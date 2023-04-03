package co.sofkau.usecase.saveorder;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveOrderUseCase implements Function<Order, Mono<Order>> {

    private final OrderGateway orderGateway;

    @Override
    public Mono<Order> apply(Order order) {
        return orderGateway.save(order);
    }

}
