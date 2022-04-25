package thaitay.com.fashion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.message.ResponseMessage;
import thaitay.com.fashion.entity.Order;
import thaitay.com.fashion.entity.ProductDetail;
import thaitay.com.fashion.entity.Status;
import thaitay.com.fashion.repository.OrderRepository;
import thaitay.com.fashion.service.OrderService;
import thaitay.com.fashion.service.ProductDetailService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductDetailService productDetailService;

    @Transactional
    @GetMapping("/order")
    public ResponseEntity<?> listOder(){
        List<Order> orders = (List<Order>) orderService.findAll();
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOder(@PathVariable Long id){
        Optional<Order> oder = orderService.findById(id);
        if (!oder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oder, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/order/user/{id}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id) {
        List<Order> orders = orderService.findAllByUsersId(id);
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        order.setStatus(Status.normal);
        orderService.save(order);
        return new ResponseEntity<>(order.getOrderId(), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("order/{id}")
    public ResponseEntity<?> updateOder(@Valid @RequestBody Order order, @PathVariable Long id){
        Optional<Order> oder1 = orderService.findById(id);
        if(!oder1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oder1.get().setDateTime(order.getDateTime());
        oder1.get().setDeliveryAddress(order.getDeliveryAddress());

        orderService.save(oder1.get());
        return new ResponseEntity<>(oder1, HttpStatus.OK);
    }

//    @DeleteMapping("/order/{id}")
//    public ResponseEntity<?> deleteOder(@PathVariable Long id){
//        Optional<Order> oder = orderService.findById(id);
//        if (!oder.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        orderService.delete(id);
//        return new ResponseEntity<>(oder, HttpStatus.OK);
//    }
    @Transactional
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOder(@PathVariable Long id){
        Optional<Order> currentOrder = orderService.findById(id);
        if (currentOrder.isPresent()){
            if(currentOrder.get().getStatus()==Status.Cancel){
                List<ProductDetail> productDetailList = productDetailService.findByOrderId(id);
                for(ProductDetail productDetail: productDetailList){
                    productDetailService.deleteProductDetail(productDetail);
                }
                orderService.deleteOrder(currentOrder.get());
                return new ResponseEntity<>(currentOrder, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ResponseMessage("Enable remove this order"), HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("order/cart/{id}")
    public ResponseEntity<Order> findByStatusAndUser_Id(@PathVariable("id") Long id) {
        Status status = Status.normal;
        Order order = orderRepository.findByStatusAndUserUserId(status, id);
        if (order != null) {
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @PutMapping("/order/toOrder")
    public ResponseEntity<?> orderToOrder(@RequestBody Order order) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Optional<Order> currentOrder = orderService.findById(order.getOrderId());
        if (currentOrder.isPresent()) {
            currentOrder.get().setDateTime(date);
            currentOrder.get().setStatus(Status.order);
            currentOrder.get().setTotal(order.getTotal());
            currentOrder.get().setPhoneOrder(order.getPhoneOrder());
            currentOrder.get().setDeliveryAddress(order.getDeliveryAddress());
            currentOrder.get().setPayment(order.getPayment());
            orderService.save(currentOrder.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @PutMapping("/order/change-status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeOrderStatus(@RequestBody String status, @PathVariable Long id) {
        Status currentStatus;
        switch (status) {
            case "order":
                currentStatus = Status.order;
                break;
            case "processing":
                currentStatus = Status.processing;
                break;
            case "Cancel":
                currentStatus = Status.Cancel;
                break;
            case "Done":
                currentStatus = Status.Done;
                break;
            case "normal":
                currentStatus = Status.normal;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        Optional<Order> currentOrder = orderService.findById(id);
        if (currentOrder.isPresent()) {
            currentOrder.get().setStatus(currentStatus);
            orderService.save(currentOrder.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
