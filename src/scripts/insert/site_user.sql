INSERT INTO site_user (client_id, user_role, nickname, password, is_deleted, created_at, modified_at)
VALUES (1, 'user', 'somebody', '12345', 'n', curdate(), curdate());

INSERT INTO site_user (client_id, user_role, nickname, password, is_deleted, created_at, modified_at)
VALUES (2, 'admin', 'admin', 'admin', 'n', curdate(), curdate());