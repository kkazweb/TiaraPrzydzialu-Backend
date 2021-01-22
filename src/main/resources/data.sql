INSERT INTO question (question_id, group_code, question, question_type, has_similar_questions)
    VALUES (1, '1', 'Czy lubisz jesc', 'ADD', false ),
           (2, 'null', 'Pytanie 2', 'ADD', false),
           (3, 'null', 'Pytanie 3', 'ADD', false),
           (4, 'null', 'Pytanie 4', 'ADD', false);

INSERT INTO answer (id, answer, question_id) VALUES
            (1, 'TAK', 1),
            (2, 'NIE', 1);

INSERT INTO answer_adds (answer_id, adds) VALUES
            (1, '123'),
            (2, '124');

INSERT INTO question_answers (question_question_id, answers_id)
    VALUES (1, 1),
           (1, 2);