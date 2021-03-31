package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.dto.ElementaryGroupForSearch;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SearchService {

    ElementaryGroupRepository elementaryGroupRepository;

    public List<ElementaryGroupForSearch> listOfElementaryGroupsContainingPhrase(String phrase){

        Locale polish = new Locale("pl_PL");
        Collator polishCollator = Collator.getInstance(polish);

        List<ElementaryGroup> groups = elementaryGroupRepository.findAllByNameContainsOrCodeContains(phrase, phrase)
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
}

