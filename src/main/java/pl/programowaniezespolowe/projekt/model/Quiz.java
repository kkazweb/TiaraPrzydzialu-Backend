package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    private List<QuestionHistory> questionsHistory;

    private List<String> groupCodes;

    private List<Question> questionList;

    private List<Long> answerIds;

    public void addQuestion(Question question){
        this.questionList.add(question);
    }

    public void removeFirstQuestion(){
        this.questionList.remove(0);
    }

    public List<String> endQuiz(){
        return this.groupCodes;
    }

    public void setQuestions(Iterable<Question> questionIterable){
        List<Question> questions = new ArrayList<>();
        questionIterable.forEach(questions::add);
        this.setQuestionList(questions);
    }

    public void addCode(String code){
        if(!this.groupCodes.contains(code))
            this.groupCodes.add(code);
    }

    public List<Question> getQuestionList(){
        return this.questionList;
    }

}
