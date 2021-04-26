package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Salary;
import pl.programowaniezespolowe.projekt.service.SalaryStatisticsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/salarystatistics")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SalaryStatisticsController {

    private final SalaryStatisticsService salaryStatisticsService;

    @GetMapping("/{groupcode}")
    public List<Salary> getSalaryStatistics(@PathVariable String groupcode){
        return salaryStatisticsService.salaryStatisticsList(groupcode);
    }
}
