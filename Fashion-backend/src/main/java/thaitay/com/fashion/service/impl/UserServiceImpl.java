package thaitay.com.fashion.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.User;
import thaitay.com.fashion.repository.UserRepository;
import thaitay.com.fashion.service.UserService;

import javax.persistence.*;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usersRepository;


    @Override
    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public void save(User users) {
        usersRepository.save(users);
    }

    @Override
    @Transient
    public Iterable<User> findAll() {
//        Session session = entityManager.unwrap(Session.class);
//        String sql = "SELECT u.userId, u.name, u.email, u.userName, u.password, r.roleId, r.roleName FROM Users u inner join Roles r on u.roles = ";
//        Query<Users> query = session.createQuery(sql);
//        List<Users> kq = query.getResultList();
//        return kq;
        Iterable<User> users = usersRepository.findAll();
//        users.forEach(users1 -> {
//            System.out.println(users1.getRoles());
//        });
        return usersRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Iterable<User> findUsersByNameContaining(String userName) {
        return usersRepository.findUsersByNameContaining(userName);
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
