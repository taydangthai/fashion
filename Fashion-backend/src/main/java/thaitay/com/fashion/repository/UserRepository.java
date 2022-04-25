package thaitay.com.fashion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.ForgotPassword;
import thaitay.com.fashion.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Iterable<User> findUsersByNameContaining(String name);
    User findByEmail(String email);
    User findByForgotPasswords(ForgotPassword forgotpassword);
}
