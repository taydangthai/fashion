package thaitay.com.fashion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Order;
import thaitay.com.fashion.entity.Status;
import thaitay.com.fashion.repository.OrderRepository;
import thaitay.com.fashion.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findByOrderId(id);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findByStatusAndUserId(Status status, Long userId) {
        return orderRepository.findByStatusAndUserUserId(status, userId);
    }

    @Override
    public List<Order> findAllByUsersId(Long userId) {
        return orderRepository.findAllByUserUserId(userId);
    }

//    @Override
//    public List<Order> findAllByUsersId(Long userId) {
//        return orderRepository.findAllByUserUserId(userId);
//    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
