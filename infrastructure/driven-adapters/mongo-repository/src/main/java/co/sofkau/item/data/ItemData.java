package co.sofkau.item.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "items")
@NoArgsConstructor
public class ItemData {

    @Id
    private String id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 15, message = "Name should be between 1 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name should contain only alphabets")
    private String name;

    @NotNull(message = "Type is required")
    @Size(min = 1, max = 15, message = "Type should be between 1 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Type should contain only alphabets")
    private String type;

    @NotNull(message = "Price is required")
    @Pattern(regexp = "^[0-9]*$", message = "Price should contain only numbers")
    private Double price;

    @NotNull(message = "Available is required")
    @Pattern(regexp = "^[0-9]*$", message = "Available should contain only numbers")
    private Integer available;

    @NotNull(message = "Description is required")
    @Size(min = 1, max = 40, message = "Description should be between 1 and 40 characters")
    private String description;

    private Boolean deleted = false;

    public ItemData(String name, String type, Double price, Integer available, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.price = price;
        this.available = available;
        this.description = description;
        this.deleted = false;
    }

}
