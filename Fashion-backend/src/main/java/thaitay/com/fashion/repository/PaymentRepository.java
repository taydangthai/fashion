package thaitay.com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaitay.com.fashion.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
