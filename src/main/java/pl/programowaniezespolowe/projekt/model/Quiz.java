package pl.programowaniezespolowe.projekt.model;

import java.util.List;

public class Quiz {

    private List<Question> askedQuestions;

    private List<String> currentAnswers;

    private Boolean ifHasSimilarQuestions(String code) {
        for(Question question: askedQuestions){
            if(question.hasSimilarQuestions)
                return true;
        }
        return false;
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

    // feature: jezeli lista pusta to obnizyc kwalfikacje (pierwsze sito cofnonc pomzdro)
    // rozluznij se kryteria synu


}
