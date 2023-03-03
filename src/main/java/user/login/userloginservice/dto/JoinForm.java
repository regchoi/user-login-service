package user.login.userloginservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class JoinForm {
    private String userId;
    private String password;
    private String name;
    @Email
    private String email;
    private String hobby;
}
