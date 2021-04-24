package pl.programowaniezespolowe.projekt.controller;

        import lombok.AllArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import pl.programowaniezespolowe.projekt.service.MailService;

        import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailController {

    private MailService mailService;

    @GetMapping("/sendEmail")
    public String sendTestEmail() throws MessagingException {
        mailService.sendEmail("297038@mat.umk.pl", "u didnt win hundred zlotys", "<b> 100 zl </b><br> 0 scam 100% legit", true);
        return "wyslano";
    }
}
