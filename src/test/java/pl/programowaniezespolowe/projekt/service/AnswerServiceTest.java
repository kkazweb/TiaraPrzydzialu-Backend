package pl.programowaniezespolowe.projekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private AnswerRepository answerRepository;

    private AnswerService answerService;

    @BeforeEach
    void setUp(){
        answerService = new AnswerService(answerRepository);
    }

    @Test
    void verifyingAnswerId1() {
        assertThat(answerService.findAnswerById(1L)).isNotNull();
        assertThat(answerService.findAnswerById(1L).getText()).isEqualTo("Matematyka");
        assertThat(answerService.findAnswerById(1L).getQuestionId()).isEqualTo(1L);
    }

    @Test
    void verifyingIfAnswerId2AddsAllGroupCodes() {
        assertThat(answerService.findAnswerById(2L).getAddsGroupCodes()).isNotNull();
        assertThat(answerService.findAnswerById(2L).getAddsGroupCodes()).asList().containsExactlyInAnyOrder(
                "2111", "216f", "23", "214f", "215");
    }

}