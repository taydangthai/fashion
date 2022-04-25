package thaitay.com.fashion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Picture;
import thaitay.com.fashion.repository.PictureRepository;
import thaitay.com.fashion.service.PictureService;

import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;
    @Override
    public List<Picture> findAllPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findByPictureId(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
