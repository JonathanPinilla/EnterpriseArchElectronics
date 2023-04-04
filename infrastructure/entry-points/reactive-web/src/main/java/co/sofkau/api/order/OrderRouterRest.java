package co.sofkau.api.order;

import co.sofkau.model.order.Order;
import co.sofkau.usecase.getallorders.GetAllOrdersUseCase;
import co.sofkau.usecase.getorderbyid.GetOrderByIdUseCase;
import co.sofkau.usecase.saveorder.SaveOrderUseCase;
import co.sofkau.usecase.softdeleteorder.SoftDeleteOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
public class OrderRouterRest {

    @Bean
    @RouterOperation(path = "/api/orders", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllOrdersUseCase.class,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllOrders", tags = "Order use cases", responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    )
    public RouterFunction<ServerResponse> getAllOrders(GetAllOrdersUseCase getAllOrdersUseCase) {
        return route(GET("/api/orders"),
                request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(getAllOrdersUseCase.get(), Order.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    @Bean
    @RouterOperation(path = "/api/orders/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetOrderByIdUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "getOrderById", tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Order id",
                                    required = true,
                                    example = "id"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> getOrderById(GetOrderByIdUseCase getOrderByIdUseCase) {
        return route(GET("/api/orders/{id}"),
                request -> getOrderByIdUseCase.apply(request.pathVariable("id"))
                        .flatMap(order -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(order)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/orders", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveOrderUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveOrder", tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "order",
                                    description = "Order to save",
                                    required = true
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Successful operation"),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    },
                    requestBody = @RequestBody(
                            required = true,
                            content = @Content(schema = @Schema(implementation = Order.class))
                    )))
    public RouterFunction<ServerResponse> saveOrder(SaveOrderUseCase saveOrderUseCase) {
        return route(POST("/api/orders").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Order.class)
                        .flatMap(order -> saveOrderUseCase.apply(order)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(error.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/api/orders/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SoftDeleteOrderUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteOrder", tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Order id to soft delete",
                                    required = true,
                                    example = "id"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> softDeleteOrder(SoftDeleteOrderUseCase softDeleteOrderUseCase) {
        return route(DELETE("/api/orders/{id}"),
                request -> softDeleteOrderUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    @RouterOperation(path = "/api/orders/delete/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SoftDeleteOrderUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "softDeleteOrder", tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "id",
                                    description = "Order id to delete",
                                    required = true,
                                    example = "id"
                            )
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Bad request")
                    })
    )
    public RouterFunction<ServerResponse> deleteOrder(SoftDeleteOrderUseCase softDeleteOrderUseCase) {
        return route(DELETE("/api/orders/delete/{id}"),
                request -> softDeleteOrderUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

}
