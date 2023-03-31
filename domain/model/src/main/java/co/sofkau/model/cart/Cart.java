package co.sofkau.model.cart;
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
public class Cart {

    private String id;
    private List<Item> items;
    private Double price;
    private Boolean deleted;

}
