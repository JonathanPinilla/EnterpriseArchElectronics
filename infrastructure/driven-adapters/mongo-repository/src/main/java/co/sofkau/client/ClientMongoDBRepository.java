package co.sofkau.client;

import co.sofkau.client.data.ClientData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientMongoDBRepository extends ReactiveMongoRepository<ClientData, String>{
}
