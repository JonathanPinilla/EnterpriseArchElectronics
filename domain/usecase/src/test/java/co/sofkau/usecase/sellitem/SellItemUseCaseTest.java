package co.sofkau.usecase.sellitem;

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
class SellItemUseCaseTest {

    @Mock
    private ItemGateway itemGateway;
    private SellItemUseCase sellItemUseCase;

    @BeforeEach
    void setUp() {
        sellItemUseCase = new SellItemUseCase(itemGateway);
    }

    @Test
    void apply() {

        var item = new Item(
                "1",
                "name",
                "type,",
                100.0,
                10,
                "description",
                false
        );

        var item2 = new Item(
                "1",
                "name",
                "type,",
                100.0,
                5,
                "description",
                false
        );

        Mockito.when(itemGateway.sell("1", 5)).thenReturn(Mono.just(item2));

        var response = sellItemUseCase.apply("1", 5);

        StepVerifier.create(response)
                .expectNext(item2)
                .verifyComplete();

    }


}