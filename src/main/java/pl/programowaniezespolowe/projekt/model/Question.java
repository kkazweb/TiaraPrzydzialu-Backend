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
    private Long questionId;

    // chyba kod grupy tez powinnismy zawrzec
    private String groupCode; // kod typu "1" lub "11"

    private String question;

    // ("1", pytanie, ADD, {})

    // odp:
    // ("tak", questionID??, {"11", "12"})
    // ("nie", questionID??, {"11", "12"})

//    @Enumerated(EnumType.STRING)
    private String questionType; // to decide whether this question is add, remove or select type

    @OneToMany
    private List<Answer> answers; // one question can have many answers

    boolean hasSimilarQuestions;
//    private List<Question> similarQuestion;
    // <- lista duplikatow

}
