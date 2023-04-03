package co.sofkau.model.client.gateways;

import co.sofkau.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientGateway {

    public Flux<Client> getAll();
    public Mono<Client> getById(String id);
    public Mono<Client> save(Client client);
    public Mono<Void> delete(String id);

}
