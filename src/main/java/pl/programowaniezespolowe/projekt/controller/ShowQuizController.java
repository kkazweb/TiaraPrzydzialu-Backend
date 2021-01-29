package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;
import pl.programowaniezespolowe.projekt.service.AnswerServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuestionServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuizServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/")
public class ShowQuizController {

    @ModelAttribute("quiz")
    public Quiz quiz(){
        return new Quiz();
    }

    @GetMapping
    public String showQuiz(Model model, @ModelAttribute("quiz") Quiz quiz){
//        List<Question> questions = quiz.getListaPytan();
//           System.out.println(questions);
//        model.addAttribute(questions);
        System.out.println(quiz);
        return "Quiz";
    }
}
