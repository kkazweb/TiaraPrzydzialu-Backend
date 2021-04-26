package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Salary;
import pl.programowaniezespolowe.projekt.repository.SalaryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SalaryStatisticsService {
    SalaryRepository salaryRepository;

    public List<Salary> salaryStatisticsList(String groupCode){
        List<Salary> statistics = new ArrayList<>();
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,1)));
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,2)));
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,3)));
        return statistics;
    }
}
