package co.sofkau.config;

import co.sofkau.model.client.gateways.ClientGateway;
import co.sofkau.model.item.gateways.ItemGateway;
import co.sofkau.model.order.gateways.OrderGateway;
import co.sofkau.usecase.deleteclient.DeleteClientUseCase;
import co.sofkau.usecase.deleteitem.DeleteItemUseCase;
import co.sofkau.usecase.deleteorder.DeleteOrderUseCase;
import co.sofkau.usecase.getallclients.GetAllClientsUseCase;
import co.sofkau.usecase.getallitems.GetAllItemsUseCase;
import co.sofkau.usecase.getallorders.GetAllOrdersUseCase;
import co.sofkau.usecase.getclientbyid.GetClientByIdUseCase;
import co.sofkau.usecase.getitembyid.GetItemByIdUseCase;
import co.sofkau.usecase.getorderbyid.GetOrderByIdUseCase;
import co.sofkau.usecase.saveclient.SaveClientUseCase;
import co.sofkau.usecase.saveitem.SaveItemUseCase;
import co.sofkau.usecase.saveorder.SaveOrderUseCase;
import co.sofkau.usecase.sellitem.SellItemUseCase;
import co.sofkau.usecase.softdeleteclient.SoftDeleteClientUseCase;
import co.sofkau.usecase.softdeleteitem.SoftDeleteItemUseCase;
import co.sofkau.usecase.softdeleteorder.SoftDeleteOrderUseCase;
import co.sofkau.usecase.updateclient.UpdateClientUseCase;
import co.sofkau.usecase.updateitem.UpdateItemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.sofkau.usecase")
public class UseCasesConfig {

        //Client Use Cases
        @Bean
        public GetAllClientsUseCase getAllClientsUseCase(ClientGateway clientGateway) {
                return new GetAllClientsUseCase(clientGateway);
        }

        @Bean
        public GetClientByIdUseCase getClientByIdUseCase(ClientGateway clientGateway) {
                return new GetClientByIdUseCase(clientGateway);
        }

        @Bean
        public SaveClientUseCase saveClientUseCase(ClientGateway clientGateway) {
                return new SaveClientUseCase(clientGateway);
        }

        @Bean
        public UpdateClientUseCase updateClientUseCase(ClientGateway clientGateway) {
                return new UpdateClientUseCase(clientGateway);
        }

        @Bean
        public SoftDeleteClientUseCase softDeleteClientUseCase(ClientGateway clientGateway) {
                return new SoftDeleteClientUseCase(clientGateway);
        }

        @Bean
        public DeleteClientUseCase deleteClientUseCase(ClientGateway clientGateway) {
                return new DeleteClientUseCase(clientGateway);
        }

        //Item Use Cases
        @Bean
        public GetAllItemsUseCase getAllItemsUseCase(ItemGateway itemGateway) {
                return new GetAllItemsUseCase(itemGateway);
        }

        @Bean
        public GetItemByIdUseCase getItemByIdUseCase(ItemGateway itemGateway) {
                return new GetItemByIdUseCase(itemGateway);
        }

        @Bean
        public SaveItemUseCase saveItemUseCase(ItemGateway itemGateway) {
                return new SaveItemUseCase(itemGateway);
        }

        @Bean
        public UpdateItemUseCase updateItemUseCase(ItemGateway itemGateway) {
                return new UpdateItemUseCase(itemGateway);
        }

        @Bean
        SellItemUseCase sellItemUseCase(ItemGateway itemGateway) {
                return new SellItemUseCase(itemGateway);
        }

        @Bean
        public SoftDeleteItemUseCase softDeleteItemUseCase(ItemGateway itemGateway) {
                return new SoftDeleteItemUseCase(itemGateway);
        }

        @Bean
        public DeleteItemUseCase deleteItemUseCase(ItemGateway itemGateway) {
                return new DeleteItemUseCase(itemGateway);
        }

        //Order Use Cases
        @Bean
        public GetAllOrdersUseCase getAllOrdersUseCase(OrderGateway orderGateway) {
                return new GetAllOrdersUseCase(orderGateway);
        }

        @Bean
        public GetOrderByIdUseCase getOrderByIdUseCase(OrderGateway orderGateway) {
                return new GetOrderByIdUseCase(orderGateway);
        }

        @Bean
        public SaveOrderUseCase saveOrderUseCase(OrderGateway orderGateway) {
                return new SaveOrderUseCase(orderGateway);
        }

        @Bean
        public SoftDeleteOrderUseCase softDeleteOrderUseCase(OrderGateway orderGateway) {
                return new SoftDeleteOrderUseCase(orderGateway);
        }

        @Bean
        public DeleteOrderUseCase deleteOrderUseCase(OrderGateway orderGateway) {
                return new DeleteOrderUseCase(orderGateway);
        }

}
