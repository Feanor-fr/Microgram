package kg.attractor.microgram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentViewDto {

    private Integer id;
    private String commentText;
    private LocalDate createdDate;
    private String ownerLogin;
    private Boolean isOwner;
}
