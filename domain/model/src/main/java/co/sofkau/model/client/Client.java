package co.sofkau.model.client;

import co.sofkau.model.cart.Cart;
import co.sofkau.model.order.Order;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {

    private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Cart cart;
    private List<Order> orders;
    private Boolean deleted;

}
