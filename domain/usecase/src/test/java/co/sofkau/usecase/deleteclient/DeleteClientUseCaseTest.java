package co.sofkau.usecase.deleteclient;

import co.sofkau.model.client.gateways.ClientGateway;
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
class DeleteClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;

    private DeleteClientUseCase deleteClientUseCase;

    @BeforeEach
    void setUp() {
        deleteClientUseCase = new DeleteClientUseCase(clientGateway);
    }

    @Test
    void apply() {

        Mockito.when(clientGateway.delete("123")).thenReturn(Mono.empty());

        var result = deleteClientUseCase.apply("123");

        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();

    }
}