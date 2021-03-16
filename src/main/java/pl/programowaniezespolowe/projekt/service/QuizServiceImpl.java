package pl.programowaniezespolowe.projekt.service;

import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl {

    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    private Quiz quiz;

    private Question getQuestion(){
        return quiz.getQuestionList().get(0);
    }

    private List<Answer> getAnswers(Question question){
        return answerRepository.findAnswersByQuestionId(question.getId());
    }

    private void saveAnswer(Question question, Answer answer){
//        quiz.updateQuestionsHistory(question, answer);
        Optional<Question> question1 = questionRepository.findById(answer.getQuestionId());
        if(question1.isPresent()){
            Question question2 = question1.get();
            quiz.addQuestion(question2);
        }

        quiz.removeFirstQuestion();
        if(questionRepository.existsByGroupCode(quiz.getGroupCodes().get(0))){
            quiz.endQuiz();
        }
    }

}
