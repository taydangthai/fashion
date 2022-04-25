package thaitay.com.fashion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.Order;
import thaitay.com.fashion.entity.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(Long orderId);
    Order findByStatusAndUserUserId(Status status, Long userId);
    List<Order> findAllByUserUserId(Long userId);
}
