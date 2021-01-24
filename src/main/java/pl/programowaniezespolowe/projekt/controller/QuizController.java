package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;
import pl.programowaniezespolowe.projekt.service.AnswerServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuestionServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuizServiceImpl;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@SessionAttributes("quiz")
public class QuizController {

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    private QuizServiceImpl quizService;

    private AnswerServiceImpl answerService;

    private QuestionServiceImpl questionService;

    @Autowired
    public QuizController(AnswerRepository answerRepository, QuestionRepository questionRepository, QuizServiceImpl quizService, AnswerServiceImpl answerService, QuestionServiceImpl questionService) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.quizService = quizService;
        this.answerService = answerService;
        this.questionService = questionService;
    }


    @ModelAttribute(name = "quiz")
    public Quiz quiz(){
        return new Quiz();
    }

    @GetMapping("/start")
    public Quiz startQuiz(Model model, Principal principal, HttpSession session){
//        HTTPSESSION session nie robi w zasadzie nic, poza tym, ze
        // przywoluje obiekt sesji do javy i mozemy na nim debugowac. fajnie
        // na model model tez mozna milo debugowac, polecamm stawiac endpointy przy wywolaniach i na koncu funkcji -
        // wtedy widac jak nasz model pracuje i co powinnismy dostawac a co dostajemy 
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCodeNull();
//        System.out.println(currentList);
        quiz.setQuestions(currentList);
//        System.out.println(quiz);
        model.addAttribute("quiz", quiz);
//        System.out.println(model);
//        System.out.println(session);
        return quiz;
    }

    @GetMapping("/form")
    public Quiz showForm(Model model, @ModelAttribute("quiz") Quiz quiz, HttpSession session) {
//        System.out.println(quiz);
        quiz.removeFirstQuestion();
        return quiz;
    }
}
