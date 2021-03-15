package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    private AnswerServiceImpl answerService;

    private QuestionServiceImpl questionService;

    private ProfessionServiceImpl professionService;

    private QuizServiceImpl quizService;

    @Autowired
    public AlgorithmServiceImpl(ProfessionServiceImpl professionService, AnswerServiceImpl answerService, QuestionServiceImpl questionService, QuizServiceImpl quizService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.quizService = quizService;
        this.professionService = professionService;
    }

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

    public Quiz showForm(Quiz quiz){
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
        QuestionHistory questionHistory1 = new QuestionHistory();
        questionHistory1.setQuestionId(quiz.getQuestionList().get(0).getId());
        List<Long> answerIds1 = new ArrayList<>();
        for(Answer answer: answers){
            answerIds1.add(answer.getId());
        }
        questionHistory1.setAnswerIds(answerIds1);
        quiz.getQuestionsHistory().add(questionHistory1);

        if(quiz.getQuestionList().size() > 0)

            quiz.getQuestionList().remove(0);
        for(int i = 0; i < answers.size(); i++){
            for(int j = 0; j < answers.get(i).getAddsGroupCodes().size(); j++){
                Question question = questionService.findQuestionByGroupCode(answers.get(i).getAddsGroupCodes().get(j));
                quiz.addQuestion(question);
                if(questionService.findQuestionsByGroupCode(answers.get(i).getAddsGroupCodes().get(j)).size() == 0){
                    quiz.addCode(answers.get(i).getAddsGroupCodes().get(j));
                }
            }
        }
        System.out.println(quiz.getQuestionList());
        quiz.getAnswerIds().clear();
        answers.clear();

        // tu sprawdzic czy kolejne pytanie nie bylo juz zadane
        while(checkIfQuestionWasAsked(quiz.getQuestionList().get(0), quiz.getQuestionsHistory())){
            // sprawdzamy dopoki pierwsze pytanie z quizu nie bylo jeszcze zadane
            List<QuestionHistory> questionsHistory = quiz.getQuestionsHistory();
            Question question = quiz.getQuestionList().get(0);
            // zmienne pomocnicze
            // dla kazdego pytania ktore bylo zadane:
            for(QuestionHistory questionHistory: questionsHistory){
                // znajdujemy to samo pytanie
                if(question.getText().equals(questionService.findById(questionHistory.getQuestionId()).getText())){
                    // pobieramy odpowiedzi ktore byly udzielone na to pytanie
                    List<Long> answerIds = questionHistory.getAnswerIds();
                    // dla kazdej odpowiedzi z udzielonych odpowiedzi:
                    for(Long id: answerIds){
                        // znajdujemy dany answer bo id mielismy
                        Answer answer = answerService.findAnswerById(id);
                        // szukamy czy odpowiedz ta znajduje sie w pytaniu QL[0]
                        for(Answer answer1: question.getAnswers()){
                            // jesli mamy te same odpowiedzi, to musimy udzielic answera z taka sama odpowiedzia
                            if(answer1.getText().equals(answer.getText())){
                                answers.add(answer1);
                            }
                        }
                    }
                }
            }
            for(int i = 0; i < answers.size(); i++){
                for(int j = 0; j < answers.get(i).getAddsGroupCodes().size(); j++){
                    Question question1 = questionService.findQuestionByGroupCode(answers.get(i).getAddsGroupCodes().get(j));
                    quiz.addQuestion(question1);
                    if(questionService.findQuestionsByGroupCode(answers.get(i).getAddsGroupCodes().get(j)).size() == 0){
                        quiz.addCode(answers.get(i).getAddsGroupCodes().get(j));
                    }
                }
            }
            answers.clear();
            quiz.getQuestionList().remove(0);
        }
        // tu skonczyc


        return quiz;
    }

    public boolean checkIfQuestionWasAsked(Question question, List<QuestionHistory> questionsHistory){
        for(QuestionHistory questionHistory: questionsHistory){
            if(question.getText().equals(questionService.findById(questionHistory.getQuestionId()).getText())){
                return true;
            }
        }
        return false;
    }

    public List<Profession> getProfessions(Quiz quiz){
        if(quiz.getQuestionList().size() > 0){
            System.out.println("Questionlist size > 0. Something went wrong.");
        }
        List<String> codes = quiz.getGroupCodes();
        List<Profession> professions = new ArrayList<>();

        for(String code: codes) {
            Optional<Profession> profession = professionService.findByCode(code);
            if(profession.isPresent()){
                professions.add(profession.get());
            }
        }

        return professions;
    }

}
