package co.sofkau.usecase.saveclient;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveClientUseCase implements Function<Client, Mono<Client>> {

    private final ClientGateway clientGateway;

    @Override
    public Mono<Client> apply(Client client) {
        return clientGateway.save(client);
    }

}
