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
    // map type isn't compatible with json data so we have to re-do this, probably another list of objects of new class
    // that imitates map (2 props - key and value)

    private List<String> groupCodes;

    private List<Question> questionList;

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

    public List<Question> getQuestionList(){
        return this.questionList;
    }

}
