package co.sofkau.api.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ItemRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ItemRouterHandler itemRouterHandler) {
        return route(GET("/api/usecase/path"), itemRouterHandler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), itemRouterHandler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), itemRouterHandler::listenGETOtherUseCase));
    }
}
