package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupCodeId;

    private double salaryAll;

    private double salaryMen;

    private double salaryWomen;

    private double employmentAll;

    private double employmentMen;

    private double employmentWomen;

    private double percentOfEmploymentAll;

    private double percentOfEmploymentForGroup1;
}