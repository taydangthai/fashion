package thaitay.com.fashion.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.entity.Sizes;
import thaitay.com.fashion.service.SizeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/size")
    public ResponseEntity<?> listSize(){
        List<Sizes> sizes = (List<Sizes>) sizeService.findAll();
        if (sizes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @GetMapping("/size/{id}")
    public ResponseEntity<?> getSize(@PathVariable Long id){
        Optional<Sizes> size = sizeService.findById(id);
        if (!size.isPresent()){
            return new ResponseEntity<>(size, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/size")
    public ResponseEntity<?> createSize(@Valid @RequestBody Sizes sizes){
        sizeService.save(sizes);
        return new ResponseEntity<>(sizes, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/size/{id}")
    public ResponseEntity<?> updateSize(@Valid @RequestBody Sizes sizes, @PathVariable Long id){
        Optional<Sizes> size1 = sizeService.findById(id);
        if (!size1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        size1.get().setSizeName(sizes.getSizeName());
        sizeService.save(size1.get());
        return new ResponseEntity<>(size1, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/size/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id){
        Optional<Sizes> size = sizeService.findById(id);
        if (!size.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sizeService.delete(id);
        return new ResponseEntity<>(size, HttpStatus.NO_CONTENT);
    }
}
