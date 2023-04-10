package co.sofkau.usecase.getallitems;

import co.sofkau.model.item.Item;
import co.sofkau.model.item.gateways.ItemGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllItemsUseCaseTest {

    @Mock
    private ItemGateway itemGateway;
    private GetAllItemsUseCase getAllItemsUseCase;

    @BeforeEach
    void setUp() {
        getAllItemsUseCase = new GetAllItemsUseCase(itemGateway);
    }

    @Test
    void get() {

        var item = new Item(
                "id",
                "name",
                "type",
                1.0,
                10,
                "description",
                "image",
                false
        );

        Mockito.when(itemGateway.getAll()).thenReturn(Flux.just(item));

        var result = getAllItemsUseCase.get();

        StepVerifier.create(result)
                .expectNext(item)
                .verifyComplete();

    }
}