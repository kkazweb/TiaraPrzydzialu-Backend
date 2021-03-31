package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.dto.ElementaryGroupForSearch;
import pl.programowaniezespolowe.projekt.dto.ProfessionForSearch;
import pl.programowaniezespolowe.projekt.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/elementarygroups")
    List<ElementaryGroupForSearch> showAllGroupsContainingPhrase(@RequestBody String phrase){
        return searchService.listOfElementaryGroupsContainingPhrase(phrase);
    }

    @PostMapping("/professions")
    List<ProfessionForSearch> showAllProfessionsContainingPhrase(@RequestBody String phrase){
        return searchService.listOfProfessionsContainingPhrase(phrase);
    }

    @PostMapping("/professions/{letter}")
    List<ProfessionForSearch> showAllProfessionsStartingWithLetter(@PathVariable String letter){
        return searchService.listOfProfessionsStartingWithLetter(letter);
    }
}
