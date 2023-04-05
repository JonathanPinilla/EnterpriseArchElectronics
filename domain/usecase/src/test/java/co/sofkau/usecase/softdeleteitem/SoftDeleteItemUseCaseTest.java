package co.sofkau.usecase.softdeleteitem;

import co.sofkau.model.item.gateways.ItemGateway;
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
class SoftDeleteItemUseCaseTest {

    @Mock
    private ItemGateway itemGateway;
    private SoftDeleteItemUseCase softDeleteItemUseCase;

    @BeforeEach
    void setUp() {
        softDeleteItemUseCase = new SoftDeleteItemUseCase(itemGateway);
    }

    @Test
    void apply() {

        Mockito.when(itemGateway.softDelete("1")).thenReturn(Mono.empty());

        var response = softDeleteItemUseCase.apply("1");

        StepVerifier.create(response)
                .verifyComplete();

    }
}