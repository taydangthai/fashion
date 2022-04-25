package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.entity.Color;
import thaitay.com.fashion.service.ColorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/color")
    public ResponseEntity<?> ListColor(){
        List<Color> colors=(List<Color>) colorService.findAll();
        if (colors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }
    @GetMapping("/color/{colorId}")
    public ResponseEntity<?> getColor(@PathVariable Long colorId){
        Optional<Color> color = colorService.findById(colorId);
        if (!color.isPresent()){
            return new ResponseEntity<>(color, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/color")
    public ResponseEntity<?> createColor(@Valid @RequestBody Color color){
        colorService.save(color);
        return new ResponseEntity<>(color, HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("/color/{colorId}")
    public ResponseEntity<?> updateColor(@Valid @RequestBody Color color, @PathVariable Long colorId){
        Optional<Color> color1 = colorService.findById(colorId);
        if (!color1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        color1.get().setColorName(color.getColorName());
        colorService.save(color1.get());
        return new ResponseEntity<>(color1, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/color/{colorId}")
    public ResponseEntity<?> deleteColor(@PathVariable Long colorId){
        Optional<Color> color = colorService.findById(colorId);
        if (!color.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        colorService.delete(colorId);
        return new ResponseEntity<>(color, HttpStatus.NO_CONTENT);
    }
}
