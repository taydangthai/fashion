package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Sizes;
import java.util.Optional;

public interface SizeService {

    Optional<Sizes> findById(Long id);
    Iterable<Sizes> findAll();
    Sizes save(Sizes sizes);
    void delete(Long id);
}
