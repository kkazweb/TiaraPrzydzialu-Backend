package pl.programowaniezespolowe.projekt.payload.response;

import lombok.Data;

@Data
public class ResetPasswordResponse {

    private String message;

    private String error;

    public ResetPasswordResponse(String message) {
        this.message = message;
    }

    public ResetPasswordResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}
