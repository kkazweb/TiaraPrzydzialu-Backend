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


    @PostMapping("/test1")
    public String test(@RequestBody String text){
        System.out.println(text);
        return text + "test2";
    }


    @GetMapping("/start")
    public Quiz startQuiz(){
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCode("start");
        HashMap<Answer, Question> map = new HashMap<>();
        List<String> codes = new ArrayList<>();
        List<Long> answerIds = new ArrayList<>();
        quiz.setGroupCodes(codes);
        quiz.setQuestions(currentList);
        quiz.setAnswerIds(answerIds);
        return quiz;
    }

    @PostMapping("/form")
    public Quiz showForm(@RequestBody Quiz quiz){
        System.out.println(quiz.getAnswerIds());
        for(int i = 0; i < quiz.getAnswerIds().size(); i++){
            System.out.println(quiz.getAnswerIds().get(i));
        }
        List<Answer> answers = new ArrayList<>();
        for(int i = 0; i < quiz.getAnswerIds().size(); i++){
            Answer answer = answerService.findAnswerById(quiz.getAnswerIds().get(i));
            answers.add(answer);
        }
        System.out.println(answers);
        if(quiz.getQuestionList().size() > 0)
            quiz.getQuestionList().remove(0);
        for(int i = 0; i < answers.size(); i++){
            for(int j = 0; j < answers.get(i).getAddsGroupCodes().size(); j++){
                Question question = questionRepository.findQuestionByGroupCode(answers.get(i).getAddsGroupCodes().get(j));
                quiz.addQuestion(question);
                if(questionRepository.findQuestionsByGroupCode(answers.get(i).getAddsGroupCodes().get(j)).size() == 0){
                    quiz.addCode(answers.get(i).getAddsGroupCodes().get(j));
                }
            }
        }
        System.out.println(quiz.getQuestionList());
        quiz.getAnswerIds().clear();
        return quiz;
    }

    // powinnismy przeniesc logike z kontrolerow do serwisow
}
