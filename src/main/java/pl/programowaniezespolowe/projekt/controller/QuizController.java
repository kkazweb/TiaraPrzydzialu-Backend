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
//@SessionAttributes("quiz")
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


//    @ModelAttribute(name = "quiz")
//    public Quiz quiz(){
//        return new Quiz();
//    }

    @PostMapping("/test1")
    public String test(@RequestBody String text){
        System.out.println(text);
        return text + "test2";
    }


    @GetMapping("/start")
    public Quiz startQuiz(){
//        public Quiz startQuiz(Model model, Principal principal, HttpSession session){
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCode("start");
        HashMap<Answer, Question> map = new HashMap<>();
        List<String> codes = new ArrayList<>();
//        List<Answer> answers = new ArrayList<>();
        List<Long> answerIds = new ArrayList<>();
        quiz.setGroupCodes(codes);
//        quiz.setQuestionsHistory(map);
        quiz.setQuestions(currentList);
//        quiz.setAnswers(answers);
        quiz.setAnswerIds(answerIds);
//        model.addAttribute("quiz", quiz);
        return quiz;
    }

    @GetMapping("/form")
    public Quiz showForm(Model model, @ModelAttribute("quiz") Quiz quiz, HttpSession session) {
        return quiz;
    }

    @PostMapping("/form")
    public Quiz showForm(@RequestBody Quiz quiz){
//        System.out.println(quiz);
//        System.out.println(quiz.getAnswers());
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
//        for(int i = 0 ; i < answers.size(); i++){
//            quiz.addHistory(answers.get(i), quiz.getQuestionList().get(0));
//        }
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
//        System.out.println(quiz);
//        System.out.println(quiz.getAnswers());
//        List<Answer> answers = quiz.getAnswers();
//        System.out.println(answers);
//        quiz.removeFirstQuestion();
//        for(int i = 0; i < answers.size(); i++){
//            for(int j = 0; j < answers.get(i).getAddsGroupCodes().size(); j++){
//                Question question = questionRepository.findQuestionByGroupCode(answers.get(i).getAddsGroupCodes().get(j));
//                quiz.addQuestion(question);
//                if(questionRepository.findQuestionsByGroupCode(answers.get(i).getAddsGroupCodes().get(j)).size() == 0){
//                    quiz.addCode(answers.get(i).getAddsGroupCodes().get(j));
//                }
//            }
//        }
//        for(int i = 0; i < quiz.getAnswers().size(); i++){
//            quiz.addHistory(quiz.getAnswers().get(i), quiz.getQuestionList().get(0));
//        }
//        for(int i = 0; i < quiz.getAnswers().size(); i++){
//            for(int j = 0; j < quiz.getAnswers().get(i).getAddsGroupCodes().size(); j++){
//                Question question = questionRepository.findQuestionByGroupCode(quiz.getAnswers().get(i).getAddsGroupCodes().get(j));
//                quiz.addQuestion(question);
//                if(quiz.getAnswers().get(i).getAddsGroupCodes().get(j).length()> 0){
//                    quiz.addCode(quiz.getAnswers().get(i).getAddsGroupCodes().get(j));
//                }
//            }
//        }
//        quiz.removeFirstQuestion();

//        quiz.addHistory(answer, quiz.getQuestionList().get(0));
//        quiz.removeFirstQuestion();
//        for(int i = 0; i < answer.getAddsGroupCodes().size(); i++){
//            Question question = questionRepository.findQuestionByGroupCode(answer.getAddsGroupCodes().get(i));
//
//            quiz.addQuestion(question);
//            if(answer.getAddsGroupCodes().size() > 0) {
//                quiz.addCode(answer.getAddsGroupCodes().get(i));
//            }
//        }
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
