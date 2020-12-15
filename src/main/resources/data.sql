INSERT INTO user(id,surname,firstname,email,mob_number)
VALUES (1,'De Almeida', 'Johann', 'johann.de-almeida@lacatholille.fr', '0766896607');

INSERT INTO user(id,surname,firstname,email,mob_number)
VALUES (2,'Giudice', 'Gianni', 'gianni.giudice@lacatholille.fr', null);

INSERT INTO user(id,surname,firstname,email,mob_number)
VALUES (3,'Guidez', 'Sébastien', 'sebastien.guidez@lacatholille.fr', null);

INSERT INTO friends(user_id, friend_id) VALUES (1, 3);
INSERT INTO friends(user_id, friend_id) VALUES (3, 1);

INSERT INTO post(text_post,public_Post,id_author,reaction)
VALUES ('ceci est le premier post', 1, 2, 4);

INSERT INTO post(text_post,public_Post,id_author,reaction)
VALUES ('ceci est le Deuxieme post', 1, 1, 9);