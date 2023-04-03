package co.sofkau.model.order.gateways;

import co.sofkau.model.order.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderGateway {

    public Flux<Order> getAll();
    public Mono<Order> getById(String id);
    public Mono<Order> save(Order order);
    public Mono<Void> delete(String id);

}
