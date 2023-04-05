package co.sofkau.usecase.updateclient;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;
    private UpdateClientUseCase updateClientUseCase;

    @BeforeEach
    void setUp() {
        updateClientUseCase = new UpdateClientUseCase(clientGateway);
    }

    @Test
    void apply() {

        var client2 = new Client(
                "1",
                "Juan",
                "Perez",
                LocalDate.now(),
                "newEmail",
                "password",
                new ArrayList<>(),
                false
        );

        Mockito.when(clientGateway.update("1",client2)).thenReturn(Mono.just(client2));

        var response = updateClientUseCase.apply("1",client2);

        StepVerifier.create(response)
                .expectNext(client2)
                .verifyComplete();

    }
}