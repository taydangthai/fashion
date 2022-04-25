package thaitay.com.fashion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Sizes;
import thaitay.com.fashion.repository.SizeRepository;
import thaitay.com.fashion.service.SizeService;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {
   @Autowired
   private SizeRepository sizeRepository;

    @Override
    public Optional<Sizes> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Iterable<Sizes> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Sizes save(Sizes sizes) {
        return sizeRepository.save(sizes);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }
}
