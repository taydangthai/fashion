package thaitay.com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.ForgotPassword;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    ForgotPassword findByToken(String token);
}
