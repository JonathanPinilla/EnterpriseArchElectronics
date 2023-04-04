package co.sofkau.usecase.updateclient;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateClientUseCase implements BiFunction<String, Client, Mono<Client>> {

    private final ClientGateway clientGateway;

    @Override
    public Mono<Client> apply(String id, Client client) {
        return clientGateway.update(id, client);
    }
}
