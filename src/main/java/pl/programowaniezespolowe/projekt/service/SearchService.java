package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.dto.ElementaryGroupForSearch;
import pl.programowaniezespolowe.projekt.dto.ProfessionForSearch;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.model.Profession;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;
import pl.programowaniezespolowe.projekt.repository.ProfessionRepository;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SearchService {

    ElementaryGroupRepository elementaryGroupRepository;
    ProfessionRepository professionRepository;

    public List<ElementaryGroupForSearch> listOfElementaryGroupsContainingPhrase(String phrase){

        Collator polishCollator = getPolishCollator();

        List<ElementaryGroup> groups = elementaryGroupRepository
                .findAllByNameContainsIgnoreCaseOrCodeContainsIgnoreCase(phrase, phrase)
                .stream()
                .sorted(Comparator.comparing(ElementaryGroup::getName, polishCollator))
                .collect(Collectors.toList());

        return groups.stream().map(this::mapElementaryGroupForSearch).collect(Collectors.toList());
    }

    private ElementaryGroupForSearch mapElementaryGroupForSearch(ElementaryGroup elementaryGroup) {
        return new ElementaryGroupForSearch(
                elementaryGroup.getCode(),
                elementaryGroup.getName(),
                elementaryGroup.getSynthesis(),
                elementaryGroup.getTasks());
    }

    public List<ProfessionForSearch> listOfProfessionsContainingPhrase(String phrase){

        Collator polishCollator = getPolishCollator();

        List<Profession> professions = professionRepository
                .findAllByNameContainsIgnoreCaseOrCodeStartsWith(phrase, phrase)
                .stream()
                .sorted(Comparator.comparing(Profession::getName, polishCollator))
                .collect(Collectors.toList());

        return professions.stream().map(this::mapProfessionForSearch).collect(Collectors.toList());
    }

    public List<ProfessionForSearch> listOfProfessionsStartingWithLetter(String letter){

        Collator polishCollator = getPolishCollator();

        List<Profession> professions = professionRepository.findAllByNameStartsWith(letter)
                .stream()
                .sorted(Comparator.comparing(Profession::getName, polishCollator))
                .collect(Collectors.toList());

        return professions.stream().map(this::mapProfessionForSearch).collect(Collectors.toList());
    }

    private ProfessionForSearch mapProfessionForSearch(Profession profession) {
        return new ProfessionForSearch(
                profession.getCode(),
                profession.getName(),
                profession.getSynthesis(),
                profession.getTasks(),
                profession.getAdditionalTasks());
    }

    private Collator getPolishCollator() {
        Locale polish = new Locale("pl_PL");
        return Collator.getInstance(polish);
    }
}

