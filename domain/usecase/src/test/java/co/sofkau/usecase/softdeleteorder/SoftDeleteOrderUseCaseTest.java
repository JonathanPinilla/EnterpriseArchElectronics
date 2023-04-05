package co.sofkau.usecase.softdeleteorder;

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
class SoftDeleteOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    private SoftDeleteOrderUseCase softDeleteOrderUseCase;

    @BeforeEach
    void setUp() {
        softDeleteOrderUseCase = new SoftDeleteOrderUseCase(orderGateway);
    }

    @Test
    void apply() {

        Mockito.when(orderGateway.softDelete("1")).thenReturn(Mono.empty());

        var response = softDeleteOrderUseCase.apply("1");

        StepVerifier.create(response)
                .verifyComplete();

    }
}