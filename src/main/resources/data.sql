INSERT INTO user(surname,firstname,email,mob_number)
VALUES ('De Almeida', 'Johann', 'johann.de-almeida@lacatholille.fr', '0766896607');

INSERT INTO user(surname,firstname,email,mob_number)
VALUES ('Giudice', 'Gianni', 'gianni.giudice@lacatholille.fr', null);

INSERT INTO user(surname,firstname,email,mob_number)
VALUES ('Guidez', 'Sébastien', 'sebastien.guidez@lacatholille.fr', null);

INSERT INTO friends(user_id, friend_id) VALUES (1, 3);
INSERT INTO friends(user_id, friend_id) VALUES (3, 1);

INSERT INTO post(text_post,public_Post,reaction, author)
VALUES ('ceci est le premier post', 1, 4, 1);

INSERT INTO post(text_post,public_Post,reaction, author)
VALUES ('ceci est le Deuxieme post', 1, 9, 1);