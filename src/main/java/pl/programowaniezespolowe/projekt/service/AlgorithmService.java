package pl.programowaniezespolowe.projekt.service;

import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.*;

import java.util.*;

@Service
public class AlgorithmService {

    private final AnswerService answerService;

    private final QuestionService questionService;

    private final ElementaryGroupService elementaryGroupService;

    public AlgorithmService(AnswerService answerService, QuestionService questionService, ElementaryGroupService elementaryGroupService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.elementaryGroupService = elementaryGroupService;
    }

    public Quiz startQuiz(){
        Quiz quiz = new Quiz();
        Iterable<Question> currentList = questionService.findAllByGroupCode("start");
        List<String> codes = new ArrayList<>();
        List<Long> answerIds = new ArrayList<>();
        List<QuestionHistory> questionHistories = new ArrayList<>();
        quiz.setGroupCodes(codes);
        quiz.setQuestions(currentList);
        quiz.setAnswerIds(answerIds);
        quiz.setQuestionsHistory(questionHistories);
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

        for (Answer value : answers) {
            for (int j = 0; j < value.getAddsGroupCodes().size(); j++) {
                Optional<List<Question>> optionalQuestion = questionService.findQuestionByGroupCode(value.getAddsGroupCodes().get(j));
                if (optionalQuestion.isEmpty()) {
                    // to oznacza ze mamy grupe elementarna lub nie ma question w bazie
                    List<String> groupCodes1 = quiz.getGroupCodes();
                    groupCodes1.add(value.getAddsGroupCodes().get(j));
                    quiz.setGroupCodes(groupCodes1);
                } else {
                    optionalQuestion.get().forEach(quiz::addQuestion);
                }

            }
        }
        System.out.println(quiz.getQuestionList());
        quiz.getAnswerIds().clear();
        answers.clear();

        while(checkIfQuestionWasAsked(quiz.getQuestionList(), quiz.getQuestionsHistory())){
            List<QuestionHistory> questionsHistory = quiz.getQuestionsHistory();
            Question question = quiz.getQuestionList().get(0);
            for(QuestionHistory questionHistory: questionsHistory){
                if(question.getText().equals(questionService.findById(questionHistory.getQuestionId()).getText())){
                    List<Long> answerIds = questionHistory.getAnswerIds();
                    for(Long id: answerIds){
                        Answer answer = answerService.findAnswerById(id);
                        for(Answer answer1: question.getAnswers()){
                            if(answer1.getText().equals(answer.getText())){
                                answers.add(answer1);
                            }
                        }
                        answers.add(answer);
                    }
                }
            }

            for (Answer answer : answers) {
                for (int j = 0; j < answer.getAddsGroupCodes().size(); j++) {
                    Optional<List<Question>> optionalQuestion = questionService.findQuestionByGroupCode(answer.getAddsGroupCodes().get(j));
                    if (optionalQuestion.isEmpty()) {
                        // to oznacza ze mamy grupe elementarna lub nie ma question w bazie
                        List<String> groupCodes1 = quiz.getGroupCodes();
                        groupCodes1.add(answer.getAddsGroupCodes().get(j));
                        quiz.setGroupCodes(groupCodes1);
                    } else {
                        optionalQuestion.get().forEach(quiz::addQuestion);
                    }
                }
            }
            answers.clear();
            quiz.getQuestionList().remove(0);
        }

        return quiz;
    }

    public boolean checkIfQuestionWasAsked(List<Question> questions, List<QuestionHistory> questionsHistory){
        if(questions.size() == 0)
            return false;
        Question question = questions.get(0);
        for(QuestionHistory questionHistory: questionsHistory){
            if(question.getText().equals(questionService.findById(questionHistory.getQuestionId()).getText())){
                return true;
            }
        }
        return false;
    }

    public List<ElementaryGroup> getGroups(Quiz quiz){
        if(quiz.getQuestionList().size() > 0){
            System.out.println("Questionlist size > 0. Something went wrong.");
        }
        List<String> codes = quiz.getGroupCodes();
        List<ElementaryGroup> elementaryGroups = new ArrayList<>();

        for(String code: codes) {
            Optional<ElementaryGroup> elementaryGroup = elementaryGroupService.findByCode(code);
            elementaryGroup.ifPresent(elementaryGroups::add);
        }

        elementaryGroups.sort(Comparator.comparing(ElementaryGroup::getCode));
        int i = 1;
        while(i < elementaryGroups.size()) {
            if(elementaryGroups.get(i).getCode().equals(elementaryGroups.get(i - 1).getCode())){
                elementaryGroups.remove(i);
            } else {
                i++;
            }
        }

        return elementaryGroups;
    }

}
