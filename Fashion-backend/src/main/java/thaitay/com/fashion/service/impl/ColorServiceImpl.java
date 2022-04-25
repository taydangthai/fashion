package thaitay.com.fashion.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Color;
import thaitay.com.fashion.repository.ColorRepository;
import thaitay.com.fashion.service.ColorService;

import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Optional<Color> findById(Long colorId) {
        return colorRepository.findById(colorId);
    }

    @Override
    public Iterable<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void delete(Long colorId) {
        colorRepository.deleteById(colorId);
    }
}
