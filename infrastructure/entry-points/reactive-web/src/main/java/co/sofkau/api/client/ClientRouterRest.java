package co.sofkau.api.client;

import co.sofkau.model.client.Client;
import co.sofkau.usecase.getallclients.GetAllClientsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouterRest {

    @Bean
    public RouterFunction<ServerResponse> getAllCliens(GetAllClientsUseCase getAllClientsUseCase) {
        return route(GET("/api/client"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllClientsUseCase.get(), Client.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }



}
