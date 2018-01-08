INSERT INTO user (user_role, nickname, password,first_name, second_name, address, about_me, birthday, is_deleted, created_at, modified_at)
VALUES ('user', 'somebody', '12345','some', 'body', 'Belarus, Hrodno', 'somebody that I used to know', '1990-10-02', 0, curdate(), curdate());

INSERT INTO user (user_role, nickname, password,first_name, second_name, address, about_me, birthday, is_deleted, created_at, modified_at)
VALUES ('admin', 'admin', 'admin','admin', 'admin', 'Belarus, Hrodno', 'some admin', '1900-01-20', 0, curdate(),
curdate());