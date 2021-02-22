package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

//    private Map<Answer, Question> questionsHistory; // key is unique (answer), value doesnt need to

    private List<String> groupCodes;

    private List<Question> questionList;

//    private List<Answer> answers;

    private List<Long> answerIds;

    public void addQuestion(Question question){
        this.questionList.add(question);
    }

    public void removeFirstQuestion(){
        this.questionList.remove(0);
    }

//    public void updateQuestionsHistory(Question question, Answer answer){
//        questionsHistory.put(answer,question);
//    }

    public List<String> endQuiz(){
        return this.groupCodes;
    }

    public void setQuestions(Iterable<Question> questionIterable){
        List<Question> questions = new ArrayList<>();
        questionIterable.forEach(questions::add);
        this.setQuestionList(questions);
    }

    public void addCode(String code){
        this.groupCodes.add(code);
    }

//    public void addHistory(Answer answer, Question question){
//        this.questionsHistory.put(answer, question);
//    }

    public List<Question> getQuestionList(){
        return this.questionList;
    }

}
