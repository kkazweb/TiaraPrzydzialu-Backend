package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/api/answers")
public class AnswerController {

// narazie to niepotrzebne

//    private AnswerRepository answerRepository;
//
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    public AnswerController(AnswerRepository answerRepository, QuestionRepository questionRepository){
//        this.answerRepository = answerRepository;
//        this.questionRepository = questionRepository;
//    }
//
//    @ModelAttribute(name = "answer")
//    public Answer answer(){
//        return new Answer();
//    }
//
//    @PostMapping
//    public String processAnswer(@Valid Answer answer, Errors errors){
//        if (errors.hasErrors()){
//            return "error";
//        }
//        System.out.println(answer.getAnswer());
//        System.out.println(answer.getAdds());
//        System.out.println(answer.getQuestionId());
//        answerRepository.save(answer);
//        Question question = questionRepository.findQuestionByQuestionId(answer.getQuestionId());
//        return "AnswersForm";
//    }
//
//    @GetMapping
//    public String showAnswersForm(Model model, Principal principal){
//        return "AnswersForm";
//    }
}
