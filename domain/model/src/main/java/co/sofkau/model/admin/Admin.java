package co.sofkau.model.admin;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Admin {

    private String id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String charge;
    private LocalDateTime lastLogin;
    private Boolean deleted;

}
