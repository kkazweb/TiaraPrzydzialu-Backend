package pl.programowaniezespolowe.projekt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HttpsConfigController {

    @GetMapping("/.well-known/acme-challenge/hk4FOIDN6GUqSGX5yeuM6Qvg2U_hh61Lp_t_H5ihAB4")
    public String longStringForConfig(){
        return "hk4FOIDN6GUqSGX5yeuM6Qvg2U_hh61Lp_t_H5ihAB4.02MM5ctQ5FjNuROhIB2v0n14vjvG9Xq9KYK0k40-APw";
    }
}
