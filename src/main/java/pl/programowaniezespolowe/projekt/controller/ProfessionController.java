package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Profession;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.service.AlgorithmServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/profession")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessionController {

    private AlgorithmServiceImpl algorithmService;

    @Autowired
    public ProfessionController(AlgorithmServiceImpl algorithmService) {
        this.algorithmService = algorithmService;
    }

    @PostMapping
    public List<Profession> listProfessionsFromQuiz(@RequestBody Quiz quiz){
        return this.algorithmService.getProfessions(quiz);
    }
}
