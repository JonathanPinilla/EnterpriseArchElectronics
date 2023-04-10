package co.sofkau.model.order;
import co.sofkau.model.item.Item;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private String id;
    private String clientId;
    private Double price;
    private String address;
    private String contactNumber;
    private List<Item> items;
    private Boolean deleted;

}
