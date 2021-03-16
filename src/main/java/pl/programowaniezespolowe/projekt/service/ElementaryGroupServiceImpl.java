package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;

import java.util.Optional;

@Service
public class ElementaryGroupServiceImpl implements ElementaryGroupService {

    private ElementaryGroupRepository elementaryGroupRepository;

    @Autowired
    public ElementaryGroupServiceImpl(ElementaryGroupRepository repository) {
        this.elementaryGroupRepository = repository;
    }

    Optional<ElementaryGroup> findByCode(String code){
        return this.elementaryGroupRepository.findByCode(code);
    }
}
