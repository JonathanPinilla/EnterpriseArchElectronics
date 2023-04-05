package co.sofkau.usecase.saveclient;

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
class SaveClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;
    private SaveClientUseCase saveClientUseCase;

    @BeforeEach
    void setUp() {
        saveClientUseCase = new SaveClientUseCase(clientGateway);
    }

    @Test
    void apply() {

        var client = new Client(
                "1",
                "name",
                "lname",
                LocalDate.now(),
                "Emaol",
                "passwrd",
                new ArrayList<>(),
                false
        );

        Mockito.when(clientGateway.save(client)).thenReturn(Mono.just(client));

        var result = saveClientUseCase.apply(client);

        StepVerifier.create(result)
                .expectNext(client)
                .verifyComplete();

    }
}