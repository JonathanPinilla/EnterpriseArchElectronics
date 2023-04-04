package co.sofkau.order;

import co.sofkau.order.data.OrderData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface OrderMongoDBRepository extends ReactiveMongoRepository<OrderData, String>{
}
