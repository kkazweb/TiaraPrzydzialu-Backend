package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.PasswordResetToken;
import pl.programowaniezespolowe.projekt.repository.PasswordTokenRepository;

import java.util.Calendar;

@Service
public class PasswordTokenService {

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Token not existing."));

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    public void removeToken(String token) {
        PasswordResetToken passwordResetToken = passwordTokenRepository.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Token not existing."));
        passwordTokenRepository.delete(passwordResetToken);

    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

}