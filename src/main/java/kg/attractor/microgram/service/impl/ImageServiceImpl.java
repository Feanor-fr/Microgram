package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.ImageDto;
import kg.attractor.microgram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<?> getById(String filename) {
        return downloadFile(filename, "images");
    }

    @Override
    public void create(ImageDto imageDto) {
        String filename = saveUploadedFile(imageDto.getFile(), "images");
        System.out.println(filename);
    }
}
