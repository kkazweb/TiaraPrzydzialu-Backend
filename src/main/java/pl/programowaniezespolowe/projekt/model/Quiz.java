package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    private Map<Answer, Question> historiaPytan; // key is unique (answer), value doesnt need to

    private List<String> listaKodow; // maybe map, for questions and answers

    private List<Question> listaPytan;

    public void addQuestion(Question question){
        this.listaPytan.add(question);
    }

    public void removeFirstQuestion(){
        this.listaPytan.remove(0);
    }

    public void updateQuestionsHistory(Question question, Answer answer){
        historiaPytan.put(answer,question);
    }

    public List<String> zakonczQuiz(){
        return this.listaKodow;
    }

    public void setQuestions(Iterable<Question> questionIterable){
        List<Question> questions = new ArrayList<>();
        questionIterable.forEach(questions::add);
        this.setListaPytan(questions);
    }

    public List<Question> getListaPytan(){
        return this.listaPytan;
    }

    // lista zadanych pytan
    // lista odpowiedzi do pytan
    // czy zadane pytanie sie powtarza
    // czy bylo juz powtorzone
    // questionType
    // askQuestion

    // start algorytmu
    // (pierwsze sito) // mozemy przechowywac "poziom" kompetencji zeby go potem obnizyc(patrz feature)
    // zwraca grupy od 0 do 9
    // kolekcojnujemy odpowiedzi do AnswersList
    // z AnswerList bierzemy 1. element i zadajemy
    // pytanie szukajac po kodzie
    // od danego kodu sprawdzamy typ pytania
    // w zaleznosci od naszego typu pytania odpowiedzi
    // beda mialy wlasnosc ADD, REMOVE, SELECT(do checkboxow lub hierarchii)
    // wybierajac odpowiedzi dodajemy kolejne odpowiedzi do listy
    // robimy to dopoki lista nie jest pusta lub nie ma samych osatatecznych kodow

    // feature: jezeli lista pusta to obnizyc kwalfikacje (pierwsze sito cofnac)


}
