package hello.toy.todoapp.member.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String loginId;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;
}