package co.sofkau.usecase.deleteitem;

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
class DeleteItemUseCaseTest {

    @Mock
    private ItemGateway itemGateway;

    private DeleteItemUseCase deleteItemUseCase;

    @BeforeEach
    void setUp() {
        deleteItemUseCase = new DeleteItemUseCase(itemGateway);
    }

    @Test
    void apply() {

        Mockito.when(itemGateway.delete("123")).thenReturn(Mono.empty());

        var result = deleteItemUseCase.apply("123");

        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();

    }
}