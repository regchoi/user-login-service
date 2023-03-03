package user.login.userloginservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userId;
    private String password;
    private String name;
    private String role;
    @Email
    private String email;
    private String hobby;
}
