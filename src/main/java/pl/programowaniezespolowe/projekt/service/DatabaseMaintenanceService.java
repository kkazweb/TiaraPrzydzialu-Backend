package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DatabaseMaintenanceService {

    @Autowired
    private PasswordTokenService passwordTokenService;


    @Scheduled(fixedRate = 86400000)
    public void scheduleTokenCleaning() { // removes expired tokens every 24hrs
        System.out.println("Scheduled task [cleaning tokens]");
        passwordTokenService.getExpiredTokens();
    }
}
