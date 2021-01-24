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


    @ModelAttribute("quiz")
    public Quiz quiz(){
        return new Quiz();
    }

    @GetMapping("/start")
    public Quiz startQuiz(Model model, Principal principal){
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCodeNull();
//        System.out.println(currentList);
        quiz.setQuestions(currentList);
        model.addAttribute(quiz);
        return quiz;
    }

    @GetMapping("/form")
    public Quiz showForm(Model model, @ModelAttribute("quiz") Quiz quiz) {
//        System.out.println(quiz);
        quiz.removeFirstQuestion();
        return quiz;
    }
}
