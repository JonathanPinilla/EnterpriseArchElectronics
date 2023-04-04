package co.sofkau.order.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
@NoArgsConstructor
public class OrderData {

    @Id
    private String id;
    private Double price;
    private String address;
    private String contactNumber;
    private Boolean deleted;

    public OrderData(Double price, String address, String contactNumber) {
        this.id = java.util.UUID.randomUUID().toString();
        this.price = price;
        this.address = address;
        this.contactNumber = contactNumber;
        this.deleted = false;
    }

}
