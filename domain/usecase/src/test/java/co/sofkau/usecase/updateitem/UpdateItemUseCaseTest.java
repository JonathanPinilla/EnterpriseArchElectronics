package co.sofkau.usecase.updateitem;

import co.sofkau.model.item.Item;
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
class UpdateItemUseCaseTest {

    @Mock
    private ItemGateway itemGateway;
    private UpdateItemUseCase updateItemUseCase;

    @BeforeEach
    void setUp() {
        updateItemUseCase = new UpdateItemUseCase(itemGateway);
    }

    @Test
    void apply() {

        var item = new Item(
                "1",
                "name",
                "type",
                100.0,
                10,
                "description",
                false
        );

        Mockito.when(itemGateway.update("1", item)).thenReturn(Mono.just(item));

        var response = updateItemUseCase.apply("1", item);

        StepVerifier.create(response)
                .expectNext(item)
                .verifyComplete();

    }
}