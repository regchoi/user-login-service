package user.login.userloginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.login.userloginservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserId(String userId);
}
