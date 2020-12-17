INSERT INTO user(pseudo, password, surname, firstname, email, mob_number)
VALUES ('dj', 'da', 'De Almeida', 'Johann', 'johann.de-almeida@lacatholille.fr', '0766896607');

INSERT INTO user(pseudo, password, surname, firstname, email, mob_number)
VALUES ('gg', 'gg', 'Giudice', 'Gianni', 'gianni.giudice@lacatholille.fr', null);

INSERT INTO user(pseudo, password, surname, firstname, email, mob_number)
VALUES ('gs', 'gs', 'Guidez', 'Sébastien', 'sebastien.guidez@lacatholille.fr', null);

INSERT INTO friends(user_id, friend_id) VALUES (1, 3);
INSERT INTO friends(user_id, friend_id) VALUES (3, 1);

INSERT INTO post(text, title, author)
VALUES ('bjr','ceci est le premier post',  1);

INSERT INTO post(text, title,ispublic, author)
VALUES ('premier post','ceci est le Deuxieme post', 0, 1);