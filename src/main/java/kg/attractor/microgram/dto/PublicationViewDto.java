package kg.attractor.microgram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PublicationViewDto{

    private Integer id;
    private String photo;
    private String description;
    private LocalDate createdDate;
    private Integer likes;
    private Integer comments;
    private String owner;
}
