package pl.programowaniezespolowe.projekt.payload.request;

import lombok.Data;

@Data
public class RemoveAccountRequest {
    private Long userId;
    private String oldPassword;
}
