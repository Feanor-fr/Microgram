package kg.attractor.microgram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDtoForView {
    private String email;
    private String password;
    private String login;
    private String userName;
    private String description;
    private String avatar;
    private Integer followers;
    private Integer subscriptions;
    private Integer publicationsCount;
}
