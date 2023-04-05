package co.sofkau.usecase.softdeleteclient;

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
class SoftDeleteClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;
    private SoftDeleteClientUseCase softDeleteClientUseCase;

    @BeforeEach
    void setUp() {
        softDeleteClientUseCase = new SoftDeleteClientUseCase(clientGateway);
    }

    @Test
    void apply() {

        Mockito.when(clientGateway.softDelete("1")).thenReturn(Mono.empty());

        var response = softDeleteClientUseCase.apply("1");

        StepVerifier.create(response)
                .verifyComplete();

    }
}