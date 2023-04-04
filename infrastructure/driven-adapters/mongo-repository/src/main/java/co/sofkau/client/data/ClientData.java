package co.sofkau.client.data;

import co.sofkau.model.order.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "clients")
@NoArgsConstructor
public class ClientData {

    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private List<Order> orders;
    private Boolean deleted;

    public ClientData(String name, String lastName, LocalDate birthDate, String email, String password, List<Order> orders) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.orders = orders;
        this.deleted = false;
    }

}
