package co.sofkau.usecase.saveorder;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SaveOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    private SaveOrderUseCase saveOrderUseCase;

    @BeforeEach
    void setUp() {
        saveOrderUseCase = new SaveOrderUseCase(orderGateway);
    }

    @Test
    void apply() {

        var order = new Order(
                "1",
                "clientId",
                10.0,
                "address",
                "213123123",
                new ArrayList<>(),
                false
        );

        Mockito.when(orderGateway.save(order)).thenReturn(Mono.just(order));

        var result = saveOrderUseCase.apply(order);

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();
    }
}