package pl.programowaniezespolowe.projekt.model;

import com.sun.istack.NotNull;
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

    boolean hasSimilarQuestions;

//    private List<Question> similarQuestions;

}
