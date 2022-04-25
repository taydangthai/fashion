package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> getAllPayment();
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    Optional<Payment> findById(Long id);

}
