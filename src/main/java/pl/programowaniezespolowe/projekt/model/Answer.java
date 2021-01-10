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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;

    private Long questionId; // questionId of question that answer is for

    @ElementCollection(targetClass = String.class)
    private List<String> adds; //code numbers, as ex. "212"
    // answer of type: yes, adds 2020
    // answer of type: biology, adds 2020, 2021



}
