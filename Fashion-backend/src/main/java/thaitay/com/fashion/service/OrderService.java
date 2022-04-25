package thaitay.com.fashion.service;

import thaitay.com.fashion.entity.Order;
import thaitay.com.fashion.entity.Status;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(Long id);
    Iterable<Order> findAll();
    Order save(Order order);
    void delete(Long id);

    Order findByStatusAndUserId(Status status, Long userId);
    List<Order> findAllByUsersId(Long userId);
    void deleteOrder(Order order);
}
