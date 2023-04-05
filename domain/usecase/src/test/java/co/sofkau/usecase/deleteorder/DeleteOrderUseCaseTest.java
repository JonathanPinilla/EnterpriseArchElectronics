package co.sofkau.usecase.deleteorder;

import co.sofkau.model.order.gateways.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    private DeleteOrderUseCase deleteOrderUseCase;

    @BeforeEach
    void setUp() {
        deleteOrderUseCase = new DeleteOrderUseCase(orderGateway);
    }

    @Test
    void apply() {

        Mockito.when(orderGateway.delete("123")).thenReturn(Mono.empty());

        var response = deleteOrderUseCase.apply("123");

        StepVerifier.create(response)
                .expectSubscription()
                .verifyComplete();

    }
}