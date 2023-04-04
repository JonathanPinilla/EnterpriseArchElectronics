package co.sofkau.client.data;

import co.sofkau.model.order.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {

    @Id
    private String id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 15, message = "Name should be between 1 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name should contain only alphabets")
    private String name;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 15, message = "Name should be between 1 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name should contain only alphabets")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is required")
    private String password;
    private List<Order> orders = new ArrayList<>();
    private Boolean deleted = false;

    public ClientData(String name, String lastName, LocalDate birthDate, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.orders = new ArrayList<>();
        this.deleted = false;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

}
