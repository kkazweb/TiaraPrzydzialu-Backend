package pl.programowaniezespolowe.projekt.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordDto {

    @Size(min = 6, max = 40)
    private String newPassword;
    private String token;
}
