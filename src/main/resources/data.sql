INSERT INTO users (login_id, password, nickname, email)
VALUES ('123', '123', '배찌', 'bazzi@com'),
       ('000', '000', '브리', 'bri@com'),
       ('111', '111', '지토', 'jito@com');

INSERT INTO posts (title, content, image_url, writer, user_id)
VALUES ('안녕하세요', '오하요', '', '배찌', 1),
       ('hi', 'gg', '', '브리', 2),
       ('hello', 'hello', '', '지토', 3);

INSERT INTO comments (writer, content, user_id, post_id)
VALUES ('배찌', '굿', 1, 1),
       ('브리', '악', 2, 1),
       ('지토', '엥?', 3, 2),
       ('배찌', '응', 1, 3),
       ('지토', '띠용?', 3, 2);
