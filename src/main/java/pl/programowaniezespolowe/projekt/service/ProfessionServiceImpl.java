package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Profession;
import pl.programowaniezespolowe.projekt.repository.ProfessionRepository;

import java.util.Optional;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    Optional<Profession> findByCode(String code){
        return this.professionRepository.findByCode(code);
    }

}
