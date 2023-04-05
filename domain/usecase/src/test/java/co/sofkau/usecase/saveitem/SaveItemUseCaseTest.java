package co.sofkau.usecase.saveitem;

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
class SaveItemUseCaseTest {

    @Mock
    private ItemGateway itemGateway;

    private SaveItemUseCase saveItemUseCase;

    @BeforeEach
    void setUp() {
        saveItemUseCase = new SaveItemUseCase(itemGateway);
    }

    @Test
    void apply() {

        var item = new Item(
                "1",
                "name",
                "type",
                10.0,
                10,
                "description",
                false
        );

        Mockito.when(itemGateway.save(item)).thenReturn(Mono.just(item));

        var result = saveItemUseCase.apply(item);

        StepVerifier.create(result)
                .expectNext(item)
                .verifyComplete();
    }
}