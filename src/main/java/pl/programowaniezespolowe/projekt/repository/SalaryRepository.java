package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.Salary;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {
    Salary findByGroupCodeId(String groupCodeId);
}
