package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.dto.SalaryStatisticsDTO;
import pl.programowaniezespolowe.projekt.model.Salary;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;
import pl.programowaniezespolowe.projekt.repository.ProfessionRepository;
import pl.programowaniezespolowe.projekt.repository.SalaryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SalaryStatisticsService {
    SalaryRepository salaryRepository;
    ProfessionRepository professionRepository;
    ElementaryGroupRepository elementaryGroupRepository;

    public SalaryStatisticsDTO salaryStatistics(String groupCode){
        String name = null;
        if(groupCode.length() > 4){
            name = professionRepository.findByCode(groupCode).getName();
        } else if(elementaryGroupRepository.findByCode(groupCode).isPresent()){
            name = elementaryGroupRepository.findByCode(groupCode).get().getName();
        }
        List<Salary> statistics = new ArrayList<>();
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,1)));
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,2)));
        statistics.add(salaryRepository.findByGroupCodeId(groupCode.substring(0,3)));
        return new SalaryStatisticsDTO(name, statistics);
    }
}
