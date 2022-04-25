package thaitay.com.fashion.service;

import thaitay.com.fashion.entity.Color;

import java.util.Optional;

public interface ColorService {

    Optional<Color> findById(Long colorId);
    Iterable<Color> findAll();
    Color save(Color color);
    void delete(Long colorId);
}
