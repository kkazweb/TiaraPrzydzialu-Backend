package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;
import pl.programowaniezespolowe.projekt.service.AlgorithmServiceImpl;
import pl.programowaniezespolowe.projekt.service.AnswerServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuestionServiceImpl;
import pl.programowaniezespolowe.projekt.service.QuizServiceImpl;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuizController {

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    private QuizServiceImpl quizService;

    private AnswerServiceImpl answerService;

    private QuestionServiceImpl questionService;

    private AlgorithmServiceImpl algorithmService;

    @Autowired
    public QuizController(AnswerRepository answerRepository, QuestionRepository questionRepository, QuizServiceImpl quizService, AnswerServiceImpl answerService, QuestionServiceImpl questionService, AlgorithmServiceImpl algorithmService) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.quizService = quizService;
        this.answerService = answerService;
        this.questionService = questionService;
        this.algorithmService = algorithmService;
    }


    @ModelAttribute(name = "quiz")
    public Quiz quiz(){
        return new Quiz();
    }


    @GetMapping("/start")
    public Quiz startQuiz(){
        return this.algorithmService.startQuiz();
    }

    @PostMapping("/form")
    public Quiz showForm(@RequestBody Quiz quiz){
        return this.algorithmService.showForm(quiz);
    }

}
