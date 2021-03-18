package pl.programowaniezespolowe.projekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class QuestionServiceImplTest {

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionServiceImpl questionService;

    @BeforeEach
    void setUp(){
        questionService = new QuestionServiceImpl(questionRepository);
    }

    @Test
    void verifyingQuestionId1() {
        assertThat(questionService.findById(1L)).isNotNull();
        assertThat(questionService.findById(1L).getText()).isEqualTo("Wybierz przedmioty które lubisz:");
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByGroupCode() {
    }

    @Test
    void findQuestionByGroupCode() {
    }

    @Test
    void checkForSimilarQuestions() {
    }
}