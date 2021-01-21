package pl.programowaniezespolowe.projekt.service;

import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.model.QuestionType;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class QuizServiceImpl implements QuizService {

    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    private Quiz quiz;

//    public void konsola(){
//        startQuiz();
//        getQuestion();
//        getAnswers();
//        saveAnswer();
//    }

    private void startQuiz(){
        List<Question> questions = questionRepository.findByGroupCodeIsNull(); // pytania z pierwszego sita
        quiz.setListaPytan(questions);
        // wywolanie getquestion
    }

    private Question getQuestion(){
        return quiz.getListaPytan().get(0);
    }

    private List<Answer> getAnswers(Question question){
        return answerRepository.findAnswersByQuestionId(question.getQuestionId());
    }

    private void saveAnswer(Question question, Answer answer){
        quiz.updateQuestionsHistory(question, answer);
        Optional<Question> question1 = questionRepository.findById(answer.getQuestionId());
        if(question1.isPresent()){
            Question question2 = question1.get();
            quiz.addQuestion(question2);
        } // tu byly problemy z castowaniem typu Optional na typ Question

        quiz.removeFirstQuestion();
        if(quiz.getListaPytan().isEmpty() || quiz.getListaPytan().get(0).getGroupCode().length() == 4) {
            quiz.zakonczQuiz();
        } // to chyba tez na froncie moze byc
//        }
//        else {
//            getQuestion();
//        }
    }



    // if has similar question
    // if similar question has been asked
    // ogolnie to to powinno byc quizservice
//    private void startQuiz(){
//        String questionNumber;
//        int flag = 0; // determines if there are only length 4 groups in currentAnswers;
//        List<Question> group0questions = questionRepository.findByGroupCodeIsNull();
//        while(!currentAnswers.isEmpty() || flag == 1){
//            questionNumber = currentAnswers.get(0);
//            currentAnswers.remove(0);
//            Question question = (Question) questionRepository.findQuestionByGroupCode(questionNumber);
//            //todo: check if question was already asked, if so use the answer
//            currentAnswers.addAll(askQuestion(question));
//            if(currentAnswers.get(0).length() == 4){
//                flag = 1;
//            }
//        }
//    }
//
//    private List<String> askQuestion(Question question){ // zwraca groupCode lub liste
//        List<Answer> answers = question.getAnswers();
//        QuestionType type = question.getQuestionType();
//
//    }

}
