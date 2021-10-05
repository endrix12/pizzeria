package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;

    public User toUserEntity() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setRole("ROLE_USER");
        return user;
    }

}
