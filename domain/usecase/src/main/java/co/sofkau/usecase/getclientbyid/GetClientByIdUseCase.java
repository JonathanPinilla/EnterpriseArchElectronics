package co.sofkau.usecase.getclientbyid;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetClientByIdUseCase implements Function<String, Mono<Client>> {

    private final ClientGateway clientGateway;

    @Override
    public Mono<Client> apply(String id) {
        return clientGateway.getById(id);
    }

}
