package co.sofkau.api.client;

import co.sofkau.model.client.Client;
import co.sofkau.usecase.deleteclient.DeleteClientUseCase;
import co.sofkau.usecase.getallclients.GetAllClientsUseCase;
import co.sofkau.usecase.getclientbyid.GetClientByIdUseCase;
import co.sofkau.usecase.saveclient.SaveClientUseCase;
import co.sofkau.usecase.softdeleteclient.SoftDeleteClientUseCase;
import co.sofkau.usecase.updateclient.UpdateClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouterRest {

    @Bean
    public RouterFunction<ServerResponse> getAllClients(GetAllClientsUseCase getAllClientsUseCase) {
        return route(GET("/api/clients"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllClientsUseCase.get(), Client.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> getClientById(GetClientByIdUseCase getClientByIdUseCase) {
        return route(GET("/api/clients/{id}"),
                request -> getClientByIdUseCase.apply(request.pathVariable("id"))
                        .flatMap(client -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(client)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> saveClient(SaveClientUseCase saveClientUseCase) {
        return route(POST("/api/clients").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Client.class)
                        .flatMap(client -> saveClientUseCase.apply(client)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> updateClient(UpdateClientUseCase updateClientUseCase) {
        return route(PUT("/api/clients/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Client.class)
                        .flatMap(client -> updateClientUseCase.apply(request.pathVariable("id"), client)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(result)))
                                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> softDeleteClient(SoftDeleteClientUseCase softDeleteClientUseCase) {
        return route(DELETE("/api/clients/{id}"),
                request -> softDeleteClientUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(result)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteClient(DeleteClientUseCase deleteClientUseCase) {
        return route(DELETE("/api/clients/delete/{id}"),
                request -> deleteClientUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(result)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

}
