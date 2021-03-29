package pl.programowaniezespolowe.projekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.programowaniezespolowe.projekt.dto.ElementaryGroupForSearch;
import pl.programowaniezespolowe.projekt.repository.ElementaryGroupRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SearchServiceTest {

    private SearchService searchService;

    @Autowired
    private ElementaryGroupRepository elementaryGroupRepository;

    @BeforeEach
    void setUp(){
        searchService = new SearchService(elementaryGroupRepository);
    }

    @Test
    void listOfElementaryGroupsContainingNamePhraseExists() {
        List<ElementaryGroupForSearch> groups = searchService.listOfElementaryGroupsContainingPhrase("sta");
        assertThat(groups).isNotEmpty();
    }

    @Test
    void listOfElementaryGroupsContainingWrongNamePhraseIsEmpty() {
        List<ElementaryGroupForSearch> groups = searchService.listOfElementaryGroupsContainingPhrase("aaa");
        assertThat(groups).isEmpty();
    }

    @Test
    void listOfElementaryGroupsContainingCodePhraseExists() {
        List<ElementaryGroupForSearch> groups = searchService.listOfElementaryGroupsContainingPhrase("011");
        assertThat(groups).isNotEmpty();
    }

    @Test
    void listOfElementaryGroupsContainingWrongCodePhraseIsEmpty() {
        List<ElementaryGroupForSearch> groups = searchService.listOfElementaryGroupsContainingPhrase("0100");
        assertThat(groups).isEmpty();
    }

}