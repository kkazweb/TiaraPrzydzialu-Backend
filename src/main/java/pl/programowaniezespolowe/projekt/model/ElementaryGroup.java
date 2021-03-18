package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ElementaryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String name;

    @OneToOne
    private ElementaryGroup parentGroup; // id kod.length - 1

    @Column(length = 1024)
    private String synthesis;

    @Column(length = 5000)
    private String tasks;
}
