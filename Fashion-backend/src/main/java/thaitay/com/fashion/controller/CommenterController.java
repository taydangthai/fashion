package thaitay.com.fashion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.entity.Commenter;
import thaitay.com.fashion.entity.Product;
import thaitay.com.fashion.service.CommenterService;
import thaitay.com.fashion.service.ProductService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class CommenterController {
    @Autowired
    private CommenterService commenterService;

    @Autowired
    private ProductService productService;


    @GetMapping("/commenter")
    public ResponseEntity<?> listCommenter(){
        List<Commenter> commenters = (List<Commenter>) commenterService.findAll();
        if (commenters.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commenters, HttpStatus.OK);
    }

    @GetMapping("/commenter/{id}")
    public ResponseEntity<?> getCommenter(@PathVariable Long id){
        Optional<Commenter> commenter = commenterService.findById(id);
        if (!commenter.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commenter, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/commenter")
    public ResponseEntity<?> createCommenter(@Valid @RequestBody Commenter commenter){
        if(commenter.getProduct().getProductId() != null) {
            Optional<Product> product = productService.findById(commenter.getProduct().getProductId());
            commenter.setProduct(product.get());
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);
        commenter.setDateTime(date);
        commenterService.save(commenter);

        return new ResponseEntity<>(commenter, HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("commenter/{id}")
    public ResponseEntity<?> updateCommenter(@Valid @RequestBody Commenter commenter, @PathVariable Long id){
        Optional<Commenter> commenter1 = commenterService.findById(id);
        if(!commenter1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);
        commenter1.get().setDateTime(date);
        commenter1.get().setContent(commenter.getContent());

        commenterService.save(commenter1.get());
        return new ResponseEntity<>(commenter1, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/commenter/{commenterId}")
    public ResponseEntity<?> deleteCommenter(@PathVariable Long commenterId){
        Optional<Commenter> commenter = commenterService.findById(commenterId);
        if (!commenter.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commenterService.delete(commenterId);
        return new ResponseEntity<>(commenter, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/commenter/product/{id}")
    public ResponseEntity<?> getAllCommentByProductId(@PathVariable Long id) {
        List<Commenter> commenters = (List<Commenter>) commenterService.findCommenterByProductId(id);

        if(commenters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(commenters, HttpStatus.OK);
    }

}
