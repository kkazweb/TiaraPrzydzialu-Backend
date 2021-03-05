package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Answer;
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
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCode("start");
        HashMap<Answer, Question> map = new HashMap<>();
        List<String> codes = new ArrayList<>();
        quiz.setGroupCodes(codes);
        //quiz.setQuestionsHistory(map);
        quiz.setQuestions(currentList);
        model.addAttribute("quiz", quiz);
        return quiz;
    }

    @GetMapping("/form")
    public Quiz showForm(Model model, @ModelAttribute("quiz") Quiz quiz, HttpSession session) {
        return quiz;
    }

    @PostMapping("/form")
    public Quiz showForm(Model mode, @ModelAttribute("quiz") Quiz quiz, @RequestBody Answer answer){

        //quiz.addHistory(answer, quiz.getQuestionList().get(0));
        quiz.removeFirstQuestion();
        for(int i = 0; i < answer.getAddsGroupCodes().size(); i++){
            Question question = questionRepository.findQuestionByGroupCode(answer.getAddsGroupCodes().get(i));

            quiz.addQuestion(question);
            if(answer.getAddsGroupCodes().size() > 0) {
                quiz.addCode(answer.getAddsGroupCodes().get(i));
            }
        }
        return quiz;
    }

    public static boolean isInteger(String s){
        if(s.isEmpty())
            return false;
        for(int i = 0; i < s.length(); i++){
            if( Character.digit(s.charAt(i), 10)<0)
                return false;
        }
        return true;
    }
}
