package pl.programowaniezespolowe.projekt.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ChangeEmailRequest {

    private Long userId;
    private String oldPassword;
    @Email
    private String emailNew;
}
