package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.service.AlgorithmService;

import java.util.List;

@RestController
@RequestMapping("/elementarygroups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ElementaryGroupController {

    private AlgorithmService algorithmService;

    @PostMapping
    public List<ElementaryGroup> listGroupsFromQuiz(@RequestBody Quiz quiz){
        return this.algorithmService.getGroups(quiz);
    }
}
