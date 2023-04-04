package co.sofkau.item;

import co.sofkau.item.data.ItemData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemMongoDBRepository extends ReactiveMongoRepository<ItemData, String>{
}
