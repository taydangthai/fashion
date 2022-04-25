package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.entity.User;
import thaitay.com.fashion.service.UserService;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UsersController {

    @Autowired
    private UserService usersService;

    @Transactional
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> List(){
        List<User> users=(List<User>) usersService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<User> user = usersService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> user = usersService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersService.delete(id);
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    //test
//    @Transactional
//    @PostMapping("/user")
//    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
//        usersService.save(user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.OK);
//    }
}
