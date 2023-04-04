package co.sofkau.model.item;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Item {

    private String id;
    private String name;
    private String type;
    private Double price;
    private Integer available;
    private String description;
    private Boolean deleted;

}
