CREATE TABLE users
(
    user_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);

CREATE TABLE posts
(
    post_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(500) NOT NULL,
    content   TEXT         NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    writer    VARCHAR(255) NOT NULL,
    user_id   BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE comments
(
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT         NOT NULL,
    writer     VARCHAR(255) NOT NULL,
    user_id    BIGINT,
    post_id    BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (post_id) ON DELETE CASCADE
);
