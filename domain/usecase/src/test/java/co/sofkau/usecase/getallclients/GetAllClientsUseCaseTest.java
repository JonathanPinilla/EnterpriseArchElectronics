package co.sofkau.usecase.getallclients;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllClientsUseCaseTest {

    @Mock
    private ClientGateway clientGateway;

    private GetAllClientsUseCase getAllClientsUseCase;

    @BeforeEach
    void setUp() {
        getAllClientsUseCase = new GetAllClientsUseCase(clientGateway);
    }

    @Test
    void get() {

        var client = new Client(
                "id",
                "Juan",
                "Perez",
                LocalDate.of(1990, 1, 1),
                "Email",
                "Password",
                new ArrayList<>(),
                false
        );

        Mockito.when(clientGateway.getAll()).thenReturn(Flux.just(client));

        var result = getAllClientsUseCase.get();

        StepVerifier.create(result)
                .expectNext(client)
                .verifyComplete();

    }
}