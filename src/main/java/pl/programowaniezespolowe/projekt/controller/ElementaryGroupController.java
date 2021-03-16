package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.service.AlgorithmServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/elementarygroups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElementaryGroupController {

    private AlgorithmServiceImpl algorithmService;

    @Autowired
    public ElementaryGroupController(AlgorithmServiceImpl algorithmService) {
        this.algorithmService = algorithmService;
    }

    @PostMapping
    public List<ElementaryGroup> listGroupsFromQuiz(@RequestBody Quiz quiz){
        return this.algorithmService.getGroups(quiz);
    }
}
