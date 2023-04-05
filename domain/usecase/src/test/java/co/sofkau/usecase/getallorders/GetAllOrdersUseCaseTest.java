package co.sofkau.usecase.getallorders;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllOrdersUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    private GetAllOrdersUseCase getAllOrdersUseCase;

    @BeforeEach
    void setUp() {
        getAllOrdersUseCase = new GetAllOrdersUseCase(orderGateway);
    }

    @Test
    void get() {

        var order = new Order(
                "id",
                10.0,
                "address",
                "123213123",
                new ArrayList<>(),
                false
        );

        Mockito.when(orderGateway.getAll()).thenReturn(Flux.just(order));

        var result = getAllOrdersUseCase.get();

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();

    }
}