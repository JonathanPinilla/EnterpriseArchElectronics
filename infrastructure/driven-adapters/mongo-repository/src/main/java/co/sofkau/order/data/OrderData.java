package co.sofkau.order.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull(message = "Price is required")
    @Pattern(regexp = "^[0-9]*$", message = "Price should contain only numbers")
    private Double price;

    @NotNull(message = "Address is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Address should contain only alphabets and numbers")
    private String address;

    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Contact number should contain only numbers")
    private String contactNumber;

    private Boolean deleted = false;

    public OrderData(Double price, String address, String contactNumber) {
        this.id = java.util.UUID.randomUUID().toString();
        this.price = price;
        this.address = address;
        this.contactNumber = contactNumber;
        this.deleted = false;
    }

}
