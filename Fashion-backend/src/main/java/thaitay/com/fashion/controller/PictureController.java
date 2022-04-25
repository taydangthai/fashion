package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import thaitay.com.fashion.dto.PictureDTO;
import thaitay.com.fashion.entity.Picture;
import thaitay.com.fashion.repository.PictureRepository;
import thaitay.com.fashion.service.PictureService;
import thaitay.com.fashion.service.impl.PictureStoreService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureStoreService pictureStoreService;

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/picture")
    public ResponseEntity<List<Picture>> findAllPicture() {
        List<Picture> pictures = pictureService.findAllPicture();
        if (pictures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PictureDTO uploadFile(@RequestParam("file") MultipartFile file) {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        name = pictureStoreService.storeFile(file);
        Picture picture = new Picture();
        picture.setPictureName(name);

        // http://localhost:8081/download/abc.jpg
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/auth/picture/downloadFromDB/")
                .path(name)
                .toUriString();

        String contentType = file.getContentType();
        picture.setSrc(url);
        pictureService.savePicture(picture);
        PictureDTO dto = new PictureDTO(name, contentType, url);
        return dto;
    }

    @GetMapping("/picture/downloadFromDB/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Picture doc = pictureRepository.findByPictureName(fileName);

        Resource resource = pictureStoreService.downloadFile(fileName);

        String mimeType = request.getServletContext().getMimeType(doc.getPictureName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+resource.getFilename()).body(resource);
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getPictureName()).body(doc.getSrc());
    }

    @GetMapping("/picture/{id}")
    public ResponseEntity<?> getAPicture(@PathVariable("id") Long id) {
        Optional<Picture> picture = pictureService.findByPictureId(id);
        if (!picture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }
    @GetMapping("/picture1/{id}")
    public ResponseEntity<?> getAllPictureByProductId(@PathVariable("id") Optional<Long> id) {
        Iterable<Picture> picture;
        if (id.isPresent()) {
            picture = pictureRepository.findPictureByProductProductId(id.get());
        }
            else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/picture/{id}")
    public ResponseEntity<?> savePicture(@PathVariable("id") Long id, @RequestBody Picture picture) {
        Optional<Picture> currentPicture = pictureService.findByPictureId(id);
        if (!currentPicture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentPicture.get().setSrc(picture.getSrc());
        pictureService.savePicture(currentPicture.get());
        return new ResponseEntity<>(currentPicture, HttpStatus.OK);
    }

    @DeleteMapping("/picture/{id}")
    public ResponseEntity<Void> removePicture(@PathVariable("id") Long id) {
        Optional<Picture> picture = pictureService.findByPictureId(id);
        if (!picture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pictureService.deletePicture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
