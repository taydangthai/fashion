package thaitay.com.fashion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Commenter;
import thaitay.com.fashion.repository.CommenterRepository;
import thaitay.com.fashion.service.CommenterService;

import java.util.Optional;

@Service
public class CommenterServiceImpl implements CommenterService {
    @Autowired
    private CommenterRepository commenterRepository;

    @Override
    public Optional<Commenter> findById(Long commenterId) {
        return commenterRepository.findById(commenterId);
    }

    @Override
    public Iterable<Commenter> findAll() {
        return commenterRepository.findAll();
    }

    @Override
    public Commenter save(Commenter commenter) {
        return commenterRepository.save(commenter);
    }

    @Override
    public void delete(Long commenterId) {
        commenterRepository.deleteById(commenterId);
    }

    @Override
    public Iterable<Commenter> findCommenterByProductId(Long productId) {
        return commenterRepository.findCommentersByProductProductId(productId);
    }

}
