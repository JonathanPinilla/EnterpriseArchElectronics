package co.sofkau.usecase.getclientbyid;

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
class GetClientByIdUseCaseTest {

    @Mock
    private ClientGateway clientGateway;
    private GetClientByIdUseCase getClientByIdUseCase;

    @BeforeEach
    void setUp() {
        getClientByIdUseCase = new GetClientByIdUseCase(clientGateway);
    }

    @Test
    void apply() {

        var client = new Client(
                "1",
                "Juan",
                "Perez",
                LocalDate.now(),
                "email",
                "password",
                new ArrayList<>(),
                false
        );

        Mockito.when(clientGateway.getById("1")).thenReturn(Mono.just(client));

        var response = getClientByIdUseCase.apply("1");

        StepVerifier.create(response)
                .expectNext(client)
                .verifyComplete();

    }
}