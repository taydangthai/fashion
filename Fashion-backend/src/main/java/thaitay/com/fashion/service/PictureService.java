package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    List<Picture> findAllPicture();
    Optional<Picture> findByPictureId(Long id);
    void savePicture(Picture picture);
    void deletePicture(Long id);
}
