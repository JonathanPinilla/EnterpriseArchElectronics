package co.sofkau.usecase.getallclients;

import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllClientsUseCase implements Supplier<Flux<Client>> {

    private final ClientGateway clientGateway;
    @Override
    public Flux<Client> get() {
        return clientGateway.getAll();
    }

}
