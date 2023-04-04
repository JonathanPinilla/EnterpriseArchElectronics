package co.sofkau.order;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import co.sofkau.order.data.OrderData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class OrderMongoRepositoryAdapter implements OrderGateway {

    private final OrderMongoDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Order> getAll() {
        return this.repository.findAll()
                .switchIfEmpty(Flux.empty())
                .map(orderData -> mapper.map(orderData, Order.class));
    }

    @Override
    public Mono<Order> getById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Order with id: " + id + " not found")))
                .map(orderData -> mapper.map(orderData, Order.class));
    }

    @Override
    public Mono<Order> save(Order order) {
        return this.repository.save(mapper.map(order, OrderData.class))
                .map(orderData -> mapper.map(orderData, Order.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error saving order: " + error)));
    }

    @Override
    public Mono<Void> softDelete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Order with id: " + id + " not found")))
                .map(orderData -> {
                    orderData.setDeleted(false);
                    return mapper.map(orderData, OrderData.class);
                })
                .flatMap(this.repository::save)
                .map(orderData -> mapper.map(orderData, Order.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error soft deleting order: " + error)))
                .then();
    }


    @Override
    public Mono<Void> delete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Order with id: " + id + " not found")))
        .flatMap(this.repository::delete);
    }
}
