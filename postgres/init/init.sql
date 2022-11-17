-- ロール
CREATE TABLE roles(
 id INTEGER PRIMARY KEY, -- ロールのID
 name VARCHAR(32) NOT NULL -- ロールの名前
);
-- ユーザー
CREATE TABLE login_user(
 id INTEGER PRIMARY KEY, -- ユーザーのID
 name VARCHAR(128) NOT NULL, -- ユーザーの表示名
 email VARCHAR(256) NOT NULL, -- メールアドレス（ログイン時に利用）
 password VARCHAR(128) NOT NULL -- ハッシュ化済みのパスワード
);
-- ユーザーとロールの対応付け
CREATE TABLE user_role(
 user_id INTEGER, -- ユーザーのID
 role_id INTEGER, -- ロールのID
 CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id),
 CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES login_user(id),
 CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles(id, name) VALUES(1, 'ROLE_GENERAL');
INSERT INTO roles(id, name) VALUES(2, 'ROLE_ADMIN');
-- password = "general"
INSERT INTO login_user(id, name, email, password) VALUES(1, '一般太郎', 'general@example.com', '$2a$10$6fPXYK.C9rCWUBifuqBIB.GRNU.nQtBpdzkkKis8ETaKVKxNo/ltO');
-- password = "admin"
INSERT INTO login_user(id, name, email, password) VALUES(2, '管理太郎', 'admin@example.com', '$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS');
INSERT INTO user_role(user_id, role_id) VALUES(1, 1);
INSERT INTO user_role(user_id, role_id) VALUES(2, 1);
INSERT INTO user_role(user_id, role_id) VALUES(2, 2);