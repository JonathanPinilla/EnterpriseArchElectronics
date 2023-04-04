package co.sofkau.api.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(OrderRouterHandler orderRouterHandler) {
        return route(GET("/api/usecase/path"), orderRouterHandler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), orderRouterHandler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), orderRouterHandler::listenGETOtherUseCase));
    }
}
