package co.sofkau.api.item;

import co.sofkau.model.item.Item;
import co.sofkau.usecase.deleteitem.DeleteItemUseCase;
import co.sofkau.usecase.getallitems.GetAllItemsUseCase;
import co.sofkau.usecase.getitembyid.GetItemByIdUseCase;
import co.sofkau.usecase.saveitem.SaveItemUseCase;
import co.sofkau.usecase.sellitem.SellItemUseCase;
import co.sofkau.usecase.softdeleteitem.SoftDeleteItemUseCase;
import co.sofkau.usecase.updateitem.UpdateItemUseCase;
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
public class ItemRouterRest {

    @Bean
    @RouterOperation(path = "/api/items", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllItemsUseCase.class,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllItems", tags = "Item use cases", responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    )
    public RouterFunction<ServerResponse> getAllItems(GetAllItemsUseCase getAllItemsUseCase) {
        return route(GET("/api/items"),
                request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(getAllItemsUseCase.get(), Item.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    @Bean
    @RouterOperation(path = "/api/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetItemByIdUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "getItemById", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Item id",
                                    required = true,
                                    in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> getItemById(GetItemByIdUseCase getItemByIdUseCase) {
        return route(GET("/api/items/{id}"),
                request -> getItemByIdUseCase.apply(request.pathVariable("id"))
                        .flatMap(item -> ServerResponse.ok()
                                .body(BodyInserters.fromValue(item)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/items", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveItemUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveItem", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "item",
                                    description = "Item to save",
                                    required = true,
                                    in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    },
                    requestBody = @RequestBody(
                            required = true,
                            content = @Content(schema = @Schema(implementation = Item.class))
                    ))
    )
    public RouterFunction<ServerResponse> saveItem(SaveItemUseCase saveItemUseCase) {
        return route(POST("/api/items").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Item.class)
                        .flatMap(item -> saveItemUseCase.apply(item)
                                .flatMap(result -> ServerResponse.status(201)
                                        .bodyValue(result))
                                .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/api/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteItemUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteItem", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Item id",
                                    required = true,
                                    in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    },
                    requestBody = @RequestBody(
                            required = true,
                            content = @Content(schema = @Schema(implementation = Item.class))
                    ))
    )
    public RouterFunction<ServerResponse> updateItem(UpdateItemUseCase updateItemUseCase) {
        return route(PUT("/api/items/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Item.class)
                        .flatMap(item -> updateItemUseCase.apply(request.pathVariable("id"), item)
                                .flatMap(result -> ServerResponse.ok()
                                        .bodyValue(result))
                                .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/api/items/{id}/sell", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SellItemUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "sellItem", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Item id",
                                    required = true,
                                    in = ParameterIn.PATH),
                            @Parameter(
                                    name = "quantity",
                                    description = "Quantity to sell",
                                    required = true,
                                    in = ParameterIn.QUERY)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    })
    )
    public RouterFunction<ServerResponse> sellItem(SellItemUseCase sellItemUseCase) {
        return route(PUT("/api/items/{id}/sell").and(accept(MediaType.APPLICATION_JSON)),
                request -> sellItemUseCase.apply(request.pathVariable("id"), request.queryParam("quantity").map(Integer::parseInt).orElse(1))
                        .flatMap(result -> ServerResponse.ok()
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SoftDeleteItemUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "buyItem", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Item id to soft delete",
                                    required = true,
                                    in = ParameterIn.PATH),
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    })
    )
    public RouterFunction<ServerResponse> softDeleteItem(SoftDeleteItemUseCase softDeleteItemUseCase) {
        return route(DELETE("/api/items/{id}"),
                request -> softDeleteItemUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/items/delete/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteItemUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteItem", tags = "Item use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Item id to delete",
                                    required = true,
                                    in = ParameterIn.PATH),
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    })
    )
    public RouterFunction<ServerResponse> deleteItem(DeleteItemUseCase deleteItemUseCase) {
        return route(DELETE("/api/items/delete/{id}"),
                request -> deleteItemUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage())));
    }

}
