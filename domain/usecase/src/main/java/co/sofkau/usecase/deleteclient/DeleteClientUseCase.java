package co.sofkau.usecase.deleteclient;

import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteClientUseCase implements Function<String, Mono<Void>> {

    private final ClientGateway clientGateway;

    @Override
    public Mono<Void> apply(String id) {
        return clientGateway.delete(id);
    }

}
