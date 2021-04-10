package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.payload.request.ChangePasswordRequest;
import pl.programowaniezespolowe.projekt.service.CredentialsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;


    @PostMapping("/changepassword")
    public String changePassword(@RequestBody ChangePasswordRequest passwordRequest) throws Exception {
        if(this.credentialsService.changePassword(passwordRequest.getUserId(), passwordRequest.getOldPassword(), passwordRequest.getNewPassword())){
            return "Hasło zmienione pomyślnie.";
        }
        return "Nie zmieniono hasła.";
    }
}
