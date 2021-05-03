package pl.programowaniezespolowe.projekt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.programowaniezespolowe.projekt.model.Salary;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStatisticsDTO {
    String professionName;
    List<Salary> statisticsForGroups;
}
