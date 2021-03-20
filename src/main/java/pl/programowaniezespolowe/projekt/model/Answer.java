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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @NotNull
    private Long questionId;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> addsGroupCodes;



}
