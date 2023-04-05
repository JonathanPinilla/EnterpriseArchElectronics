package co.sofkau.usecase.getorderbyid;

import co.sofkau.model.order.Order;
import co.sofkau.model.order.gateways.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetOrderByIdUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    private GetOrderByIdUseCase getOrderByIdUseCase;

    @BeforeEach
    void setUp() {
        getOrderByIdUseCase = new GetOrderByIdUseCase(orderGateway);
    }

    @Test
    void apply() {

        var order = new Order(
                "1",
                100.0,
                "address",
                "123456789",
                new ArrayList<>(),
                false
        );

        var result = getOrderByIdUseCase.apply("1");

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();

    }
}