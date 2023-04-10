package co.sofkau.usecase.getitembyid;

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
class GetItemByIdUseCaseTest {

    @Mock
    private ItemGateway itemGateway;
    private GetItemByIdUseCase getItemByIdUseCase;

    @BeforeEach
    void setUp() {
        getItemByIdUseCase = new GetItemByIdUseCase(itemGateway);
    }

    @Test
    void apply() {

        var item = new Item(
                "1",
                "Item name",
                "type",
                100.0,
                11,
                "description",
                "image",
                false
        );

        Mockito.when(itemGateway.getById("1")).thenReturn(Mono.just(item));

        var response = getItemByIdUseCase.apply("1");

        StepVerifier.create(response)
                .expectNext(item)
                .verifyComplete();

    }
}