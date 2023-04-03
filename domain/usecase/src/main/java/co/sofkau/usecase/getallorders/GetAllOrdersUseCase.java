package co.sofkau.usecase.getallorders;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllOrdersUseCase implements Supplier<Flux<Order>> {

    private final OrderGateway orderGateway;

    @Override
    public Flux<Order> get() {
        return orderGateway.getAll();
    }

}
