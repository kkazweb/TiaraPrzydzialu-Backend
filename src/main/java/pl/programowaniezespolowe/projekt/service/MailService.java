package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailService {

    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String title, String content, boolean isHtmlContent) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content, isHtmlContent);
        javaMailSender.send(mimeMessage);

    }
}
