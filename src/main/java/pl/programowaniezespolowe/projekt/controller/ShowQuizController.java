package pl.programowaniezespolowe.projekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Quiz;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShowQuizController {

    @ModelAttribute("quiz")
    public Quiz quiz(){
        return new Quiz();
    }

    @GetMapping
    public String showQuiz(Model model, @ModelAttribute("quiz") Quiz quiz){
        System.out.println(quiz);
        return "Quiz";
    }
}
