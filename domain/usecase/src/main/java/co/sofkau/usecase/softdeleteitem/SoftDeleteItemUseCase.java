package co.sofkau.usecase.softdeleteitem;

import co.sofkau.model.item.gateways.ItemGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SoftDeleteItemUseCase implements Function<String, Mono<Void>>{

        private final ItemGateway itemGateway;

        @Override
        public Mono<Void> apply(String id) {
            return itemGateway.softDelete(id);
        }

}
