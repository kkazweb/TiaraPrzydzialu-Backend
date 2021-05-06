package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

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

//    public void sendResetTokenEmail(String to, String title, String content, boolean isHtmlContent) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        mimeMessageHelper.setTo(to);
//        mimeMessageHelper.setSubject(title);
//        mimeMessageHelper.setText(content, isHtmlContent);
//        javaMailSender.send(mimeMessage);
//
//    }

    }
