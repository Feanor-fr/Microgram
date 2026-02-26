package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.microgram.customAnnotations.MultipartFileNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationCreateDto {

    @MultipartFileNotEmpty(message = "Вы не выбрали файл!")
    private MultipartFile file;

    @NotBlank(message = "Описание не может быть пустым!")
    private String description;
}
