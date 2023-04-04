package co.sofkau.api.client;

import co.sofkau.model.client.Client;
import co.sofkau.usecase.deleteclient.DeleteClientUseCase;
import co.sofkau.usecase.getallclients.GetAllClientsUseCase;
import co.sofkau.usecase.getclientbyid.GetClientByIdUseCase;
import co.sofkau.usecase.saveclient.SaveClientUseCase;
import co.sofkau.usecase.softdeleteclient.SoftDeleteClientUseCase;
import co.sofkau.usecase.updateclient.UpdateClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
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
    @RouterOperation(path = "/api/clients", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllClientsUseCase.class,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllClients", tags = "Client use cases", responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    )
    public RouterFunction<ServerResponse> getAllClients(GetAllClientsUseCase getAllClientsUseCase) {
        return route(GET("/api/clients"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllClientsUseCase.get(), Client.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    @Bean
    @RouterOperation(path = "/api/clients/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetClientByIdUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "getClientById", tags = "Client use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "The id of the client",
                                    required = true,
                                    in = ParameterIn.PATH,
                                    example = "642c56199cee5868b26f6cf5"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> getClientById(GetClientByIdUseCase getClientByIdUseCase) {
        return route(GET("/api/clients/{id}"),
                request -> getClientByIdUseCase.apply(request.pathVariable("id"))
                        .flatMap(client -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(client)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/clients", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveClientUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveClient", tags = "Client use cases",
                    parameters = {
                            @Parameter(
                                    name = "client",
                                    description = "The client to save",
                                    in = ParameterIn.PATH,
                                    schema = @Schema(implementation = Client.class)
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    },
                    requestBody = @RequestBody(
                            required = true,
                            content = @Content(schema = @Schema(implementation = Client.class))
                    )))
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
    @RouterOperation(path = "/api/clients/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteClientUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteClient", tags = "Client use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "The id of the client",
                                    required = true,
                                    in = ParameterIn.PATH,
                                    example = "642c56199cee5868b26f6cf5"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    },
                    requestBody = @RequestBody(
                            required = true,
                            content = @Content(schema = @Schema(implementation = Client.class))
                    ))
    )
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
    @RouterOperation(path = "/api/clients/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SoftDeleteClientUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "softDeleteClient", tags = "Client use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "The id of the client to soft delete",
                                    required = true,
                                    in = ParameterIn.PATH,
                                    example = "642c56199cee5868b26f6cf5"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> softDeleteClient(SoftDeleteClientUseCase softDeleteClientUseCase) {
        return route(DELETE("/api/clients/{id}"),
                request -> softDeleteClientUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(result)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/clients/delete/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteClientUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteClient", tags = "Client use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "The id of the client to delete",
                                    required = true,
                                    in = ParameterIn.PATH,
                                    example = "642c56199cee5868b26f6cf5"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> deleteClient(DeleteClientUseCase deleteClientUseCase) {
        return route(DELETE("/api/clients/delete/{id}"),
                request -> deleteClientUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(result)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

}
