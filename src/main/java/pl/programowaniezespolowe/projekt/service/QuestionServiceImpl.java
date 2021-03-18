package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionServiceImpl {

    private final QuestionRepository questionRepository;

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Iterable<Question> findAll(){
        return questionRepository.findAll();
    }

    public List<Question> findQuestionsByGroupCode(String code){
        return questionRepository.findQuestionsByGroupCode(code);
    }

    public Iterable<Question> findAllByGroupCode(String code){
        return questionRepository.findQuestionsByGroupCode(code);
    }

    public Question findQuestionByGroupCode(String code){
        return questionRepository.findQuestionByGroupCode(code);
    }

    public void checkForSimilarQuestions(){
        System.out.println("Number of Q-s in DB:");
        int n = questionRepository.findAll().size();
        System.out.println((long) n);
        System.out.println(n);
        boolean[][] tab = new boolean[n+1][n+1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                tab[i][j] = false;
            }
        }
//        Question question = questionRepository.findQuestionById((long) 1);
        for(int i = 1; i <= n; i++){
            Question questioni = questionRepository.findQuestionById((long) i);
            for(int j = 1; j <= n; j++){
                Question questionj =  questionRepository.findQuestionById((long) j);
                if(questioni.getText().equals(questionj.getText())){
                    tab[i][j] = true;
                    tab[j][i] = true;
                }
                System.out.println("Obieg " + i + ", " + j);
            }
        }

        System.out.println("Wynikowa: ");
        for(int i = 1; i <= n; i++) {
            System.out.println("Wiersz: " + i);
            for(int j = 1; j <= n; j++) {
                if(i == j)
                    continue;
                if(tab[i][j]){
                    System.out.println("Question o id " + i + "jest similar z question o id " + j);
//                    questionRepository.findQuestionById((long) i).setHasSimilarQuestions(true);
//                    questionRepository.findQuestionById((long) i).addSimilarQuestion((long) j);
                    Question question = questionRepository.findQuestionById((long) i);
                    question.setHasSimilarQuestions(true);
                    question.addSimilarQuestion((long) j);
                    questionRepository.save(question);
                }
            }
        }
    }
}
