package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.payload.request.LoginRequest;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

@Service
public class CredentialsService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    private void changePassword(String password){

    }

}
