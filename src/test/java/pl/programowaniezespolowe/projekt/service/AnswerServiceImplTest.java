package pl.programowaniezespolowe.projekt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AnswerServiceImplTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void verifyingAnswerId1() {
        AnswerServiceImpl answerService = new AnswerServiceImpl(answerRepository);

        assertThat(answerService.findAnswerById(1L)).isNotNull();
        assertThat(answerService.findAnswerById(1L).getText()).isEqualTo("Matematyka");
        assertThat(answerService.findAnswerById(1L).getQuestionId()).isEqualTo(1L);
    }

    @Test
    void verifyingIfAnswerId2AddsAllGroupCodes() {
        AnswerServiceImpl answerService = new AnswerServiceImpl(answerRepository);

        assertThat(answerService.findAnswerById(2L).getAddsGroupCodes()).isNotNull();
        assertThat(answerService.findAnswerById(2L).getAddsGroupCodes()).asList().containsExactlyInAnyOrder("2111",
                "216f", "23", "214f", "215");
    }

}