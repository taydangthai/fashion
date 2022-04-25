package thaitay.com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.Picture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Picture findByPictureName(String pictureName);
    Iterable<Picture> findPictureByProductProductId(Long productId);
}
