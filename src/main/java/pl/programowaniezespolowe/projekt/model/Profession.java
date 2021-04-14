package pl.programowaniezespolowe.projekt.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String name;

    @OneToOne
    private Profession parentId; // id kod.length - 1

    @Column(length = 5000)
    private String synthesis;

    @Column(length = 5000)
    private String tasks;

    @Column(length = 5000)
    private String additionalTasks;

    private String url;

    private Boolean infoDoradca;

}
