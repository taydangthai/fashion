package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Commenter;
import java.util.Optional;

public interface CommenterService {

    Optional<Commenter> findById(Long commenterId);
    Iterable<Commenter> findAll();
    Commenter save(Commenter commenter);
    void delete(Long commenterId);
    Iterable<Commenter> findCommenterByProductId(Long productId);
}
