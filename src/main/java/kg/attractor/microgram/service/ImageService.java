package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.ImageDto;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface ImageService {

    @SneakyThrows
    default String saveUploadedFile(MultipartFile file, String subDir){

        checkImages(file);

        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get("data/" + subDir);
        Files.createDirectories(pathDir);
        Path filePath = Paths.get(pathDir + "/" + fileName);

        if (!Files.exists(filePath)) Files.createFile(filePath);

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileName;
    }

    default void checkImages(MultipartFile file){
        if (file.isEmpty()){
            throw new RuntimeException("Файл пуст!");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Можно загружать только изображения!");
        }

        String checkFileType = file.getOriginalFilename();
        if (checkFileType == null) {
            throw new RuntimeException("Некорректное имя файла");
        }

        checkFileType = checkFileType.toLowerCase();
        if (!(checkFileType.endsWith(".png")
                || checkFileType.endsWith(".jpg")
                || checkFileType.endsWith(".jpeg"))) {

            throw new RuntimeException("Разрешены только PNG и JPG изображения");
        }
    }

    default void deleteFile(String filename, String subDir) {
        if (filename == null || filename.isBlank()) return;

        try {
            Path path = Paths.get("data/" + subDir + "/" + filename);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default ResponseEntity<?> downloadFile(String filename, String subDir){
        try {
            Path path = Paths.get("data/" + subDir + "/" + filename);
            byte[] image = Files.readAllBytes(path);

            String contentType = Files.probeContentType(path);
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Можно загружать только изображения!");
            }

            Resource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }


    ResponseEntity<?> getById(String filename);

    void create(ImageDto imageDto);
}
