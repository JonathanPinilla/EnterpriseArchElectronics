package co.sofkau.api.order;

import co.sofkau.model.order.Order;
import co.sofkau.usecase.getallorders.GetAllOrdersUseCase;
import co.sofkau.usecase.getorderbyid.GetOrderByIdUseCase;
import co.sofkau.usecase.saveorder.SaveOrderUseCase;
import co.sofkau.usecase.softdeleteorder.SoftDeleteOrderUseCase;
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
    public RouterFunction<ServerResponse> getAllOrders(GetAllOrdersUseCase getAllOrdersUseCase) {
        return route(GET("/api/orders"),
                request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(getAllOrdersUseCase.get(), Order.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> getOrderById(GetOrderByIdUseCase getOrderByIdUseCase) {
        return route(GET("/api/orders/{id}"),
                request -> getOrderByIdUseCase.apply(request.pathVariable("id"))
                        .flatMap(order -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(order)))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
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
    public RouterFunction<ServerResponse> softDeleteOrder(SoftDeleteOrderUseCase softDeleteOrderUseCase) {
        return route(DELETE("/api/orders/{id}"),
                request -> softDeleteOrderUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteOrder(SoftDeleteOrderUseCase softDeleteOrderUseCase) {
        return route(DELETE("/api/orders/delete/{id}"),
                request -> softDeleteOrderUseCase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
                        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage())));
    }

}
