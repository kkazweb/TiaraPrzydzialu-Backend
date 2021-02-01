package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Map;

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

//    @GetMapping("/start")
//    public Quiz startQuiz(Model model, Principal principal, HttpSession session){
////        HTTPSESSION session nie robi w zasadzie nic, poza tym, ze
//        // przywoluje obiekt sesji do javy i mozemy na nim debugowac. fajnie
//        // na model model tez mozna milo debugowac, polecamm stawiac endpointy przy wywolaniach i na koncu funkcji -
//        // wtedy widac jak nasz model pracuje i co powinnismy dostawac a co dostajemy
//        Quiz quiz = new Quiz();
//        Iterable<Question> currentList = questionService.findAllByGroupCodeNull();
////        System.out.println(currentList);
//        quiz.setQuestions(currentList);
////        System.out.println(quiz);
//        model.addAttribute("quiz", quiz);
////        System.out.println(model);
////        System.out.println(session);
//        return quiz;
//    }

    @GetMapping("/start")
    public Quiz startQuiz(Model model, Principal principal, HttpSession session){
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCode("start");
        HashMap<Answer, Question> map = new HashMap<>();
        List<String> codes = new ArrayList<>();
        quiz.setListaKodow(codes);
        quiz.setHistoriaPytan(map);
        quiz.setQuestions(currentList);
        model.addAttribute("quiz", quiz);
        return quiz;
    }

    @GetMapping("/form")
    public Quiz showForm(Model model, @ModelAttribute("quiz") Quiz quiz, HttpSession session) {
//        System.out.println(quiz);
//        quiz.removeFirstQuestion();
        return quiz;
    }

    @PostMapping("/form")
    public Quiz showForm(Model mode, @ModelAttribute("quiz") Quiz quiz, @RequestBody Answer answer){

        quiz.addHistory(answer, quiz.getListaPytan().get(0));
        quiz.removeFirstQuestion();
        for(int i = 0; i < answer.getAdds().size(); i++){
            Question question = questionRepository.findQuestionByGroupCode(answer.getAdds().get(i));

            quiz.addQuestion(question);
            if(answer.getAdds().get(i).length()==4 && isInteger(answer.getAdds().get(i))){
                quiz.addCode(answer.getAdds().get(i));
            } // utworzyc zmienna bo 4 razy wykorzystywalem
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
