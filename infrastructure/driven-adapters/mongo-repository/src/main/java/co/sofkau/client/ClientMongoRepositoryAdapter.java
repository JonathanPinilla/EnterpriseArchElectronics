package co.sofkau.client;

import co.sofkau.client.data.ClientData;
import co.sofkau.model.client.Client;
import co.sofkau.model.client.gateways.ClientGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ClientMongoRepositoryAdapter implements ClientGateway {

    private final ClientMongoDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Client> getAll() {
        return this.repository.findAll()
                .switchIfEmpty(Flux.empty())
                .map(clientData -> mapper.map(clientData, Client.class));
    }

    @Override
    public Mono<Client> getById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Client with id: " + id + " not found")))
                .map(clientData -> mapper.map(clientData, Client.class));
    }

    @Override
    public Mono<Client> save(Client client) {
        return this.repository.save(mapper.map(client, ClientData.class))
                .map(clientData -> mapper.map(clientData, Client.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error saving client: " + error)));
    }

    @Override
    public Mono<Client> update(String id, Client client) {
        return this.repository.findById(client.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Client with id: " + client.getId() + " not found")))
                .map(clientData -> {
                    clientData.setEmail(client.getEmail());
                    clientData.setPassword(client.getPassword());
                    clientData.setOrders(client.getOrders());
                    return mapper.map(client, ClientData.class);
                })
                .flatMap(this.repository::save)
                .map(clientData -> mapper.map(clientData, Client.class))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error updating client: " + error)));
    }

    @Override
    public Mono<Void> softDelete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Client with id: " + id + " not found")))
                .map(clientData -> {
                    clientData.setDeleted(true);
                    return clientData;
                })
                .flatMap(this.repository::save)
                .then()
                .onErrorResume(error -> Mono.error(new RuntimeException("Error deleting client: " + error)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Client with id: " + id + " not found")))
                .flatMap(this.repository::delete)
                .onErrorResume(error -> Mono.error(new RuntimeException("Error deleting client: " + error)));
    }
}
