package co.sofkau.item.data;

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
    private String name;
    private String type;
    private Double price;
    private Integer available;
    private String description;
    private Boolean deleted;

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
