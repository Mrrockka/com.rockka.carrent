INSERT INTO user (user_role, nickname, password, is_deleted, created_at, modified_at)
VALUES ('user', 'somebody', '12345', 'n', curdate(), curdate());

INSERT INTO user (user_role, nickname, password, is_deleted, created_at, modified_at)
VALUES ('admin', 'admin', 'admin', 'n', curdate(), curdate());