package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupCode;

    private String text;

    @OneToMany
    private List<Answer> answers;

    private boolean hasSimilarQuestions;


    @ElementCollection(targetClass = Long.class)
    private List<Long> similarQuestionIds;

    public void addSimilarQuestion(Long id){
        this.similarQuestionIds.add(id);
    }

    private Boolean multipleChoice;
}
