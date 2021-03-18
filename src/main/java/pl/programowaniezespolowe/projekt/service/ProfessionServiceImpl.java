package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Profession;
import pl.programowaniezespolowe.projekt.repository.ProfessionRepository;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessionServiceImpl {

    private final ProfessionRepository professionRepository;

    Optional<Profession> findByCode(String code){
        return this.professionRepository.findByCode(code);
    }

}
