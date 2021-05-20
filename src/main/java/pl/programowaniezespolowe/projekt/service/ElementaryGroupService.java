package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;

import java.util.Optional;

@Service
public class ElementaryGroupService {

    private ElementaryGroupRepository elementaryGroupRepository;

    @Autowired
    public ElementaryGroupService(ElementaryGroupRepository repository) {
        this.elementaryGroupRepository = repository;
    }

    ElementaryGroup findByCode(String code){
        return this.elementaryGroupRepository.findByCode(code).orElseThrow(() -> new ExpressionException("Couldn't find elementary group with code: " + code));
    }
}
