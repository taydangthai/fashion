package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String userName);
//    User findByUsername(String userName);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String userName);
    Optional<User> findById(Long id);
    void save(User user);
    Iterable<User> findAll();
    void delete(Long id);
    Iterable<User> findUsersByNameContaining(String userName);
    User findByEmail(String email);
}
