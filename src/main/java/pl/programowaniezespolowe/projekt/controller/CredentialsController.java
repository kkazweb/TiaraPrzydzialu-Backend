package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.dto.PasswordDto;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.payload.request.ChangeEmailRequest;
import pl.programowaniezespolowe.projekt.payload.request.ChangePasswordRequest;
import pl.programowaniezespolowe.projekt.payload.request.RemoveAccountRequest;
import pl.programowaniezespolowe.projekt.payload.response.ResetPasswordResponse;
import pl.programowaniezespolowe.projekt.service.CredentialsService;
import pl.programowaniezespolowe.projekt.service.MailService;
import pl.programowaniezespolowe.projekt.service.PasswordTokenService;
import pl.programowaniezespolowe.projekt.service.UserDetailsServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/credentials")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CredentialsController {

    private CredentialsService credentialsService;

    private UserDetailsServiceImpl userDetailsService;

    private MailService mailService;

    private PasswordTokenService passwordTokenService;

    @PostMapping("/resetPassword")
    public ResetPasswordResponse resetPassword(@RequestBody String email) throws MessagingException {
        User user = userDetailsService.findByEmail(email);
        String token = UUID.randomUUID().toString();
        userDetailsService.createPasswordResetTokenForUser(user, token);
        String title = "Zmiana hasła do Tiary Przydziału";
        String content = "Cześć!\n Aby zmienić hasło do Twojego konta w Tiarze Przydziału skopiuj poniższy kod i wklej go na stronie:\n " + token + "\nPozdrawiamy,\n Zespół U Mnie Działa!";
        String url = "http://localhost:8080/api/credentials/resetPassword?token=" + token;
        try {
            mailService.sendEmail(email, title, content, false);
        } catch (Exception e){
            return new ResetPasswordResponse("Nie udalo sie wyslac tokenu", "404");
        }
        return new ResetPasswordResponse("Token wyslany", "200");
    }

    @GetMapping("/resetPassword/{token}")
    public ResetPasswordResponse showChangePasswordPage(@PathVariable String token){
        if(passwordTokenService.validatePasswordResetToken(token) == null){
            return new ResetPasswordResponse("Token is valid.", "200");
        }
        return new ResetPasswordResponse("Token invalid.", "404");
    }

    @PostMapping("/resetPassword/save")
    public ResetPasswordResponse savePassword(@RequestBody PasswordDto passwordDto){
        if(passwordTokenService.validatePasswordResetToken(passwordDto.getToken()) != null){
            return new ResetPasswordResponse("Token is invalid.", "404");
        }
        User user = userDetailsService.findUserByPasswordToken(passwordDto.getToken());
        credentialsService.resetPassword(user.getId(), passwordDto.getNewPassword());
        return new ResetPasswordResponse("Password is successfully changed.", "200");

    }


    @PostMapping("/changepassword")
    @PreAuthorize("hasRole('USER')")
    public String changePassword(@RequestBody ChangePasswordRequest passwordRequest) throws Exception {
        if(this.credentialsService.changePassword(passwordRequest.getUserId(), passwordRequest.getOldPassword(), passwordRequest.getNewPassword())){
            return "Hasło zmienione pomyślnie.";
        }
        return "Nie zmieniono hasła.";
    }

    @PostMapping("/changeemail")
    @PreAuthorize("hasRole('USER')")
    public String changeEmail(@RequestBody @Valid ChangeEmailRequest emailRequest) throws Exception {
        if(this.credentialsService.changeEmail(emailRequest.getUserId(), emailRequest.getOldPassword(), emailRequest.getEmailNew())){
            return "Email zmieniony pomyślnie.";
        }
        return "Nie zmieniono maila.";
    }

    @PostMapping("/deleteaccount")
    @PreAuthorize("hasRole('USER')")
    public String deleteaccount(@RequestBody RemoveAccountRequest removeAccountRequest) throws Exception {
        if(this.credentialsService.deleteAccount(removeAccountRequest.getUserId(), removeAccountRequest.getOldPassword())){
            return "Konto zostało usunięte.";
        }
        return "Nie usunięto konta.";
    }

}
