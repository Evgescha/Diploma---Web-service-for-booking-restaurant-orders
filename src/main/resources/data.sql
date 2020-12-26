INSERT IGNORE INTO role(id, name) VALUES(1,'ROLE_USER');
INSERT IGNORE INTO role(id, name) VALUES(2,'ROLE_ADMIN');

INSERT IGNORE INTO status(id, name) VALUES(1,'Создано');
INSERT IGNORE INTO status(id, name) VALUES(2,'Отклонено');
INSERT IGNORE INTO status(id, name) VALUES(3,'Одобрено');
INSERT IGNORE INTO status(id, name) VALUES(4,'Выполнено');


INSERT IGNORE INTO my_users(id, username, password,firstname,lastname,role_id) VALUES (1,'admin','$2a$10$7kQ1nv74qr7CiGAouEzxUOqoD9Pylh7nKY6WXDzAV6O1IF5R21tz.','admin','admin',2);