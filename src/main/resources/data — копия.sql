INSERT IGNORE INTO role(id, name) VALUES(1,'ROLE_USER');
INSERT IGNORE INTO role(id, name) VALUES(2,'ROLE_ADMIN');

INSERT IGNORE INTO OrderStatus(id, name) VALUES(1,'В процессе создания');
INSERT IGNORE INTO OrderStatus(id, name) VALUES(2,'На складе');
INSERT IGNORE INTO OrderStatus(id, name) VALUES(3,'У перевозчика');
INSERT IGNORE INTO OrderStatus(id, name) VALUES(4,'Выполнен');

INSERT IGNORE INTO my_users(id, username, password,fio,role_id) VALUES (1,'admin','$2a$10$7kQ1nv74qr7CiGAouEzxUOqoD9Pylh7nKY6WXDzAV6O1IF5R21tz.','admin',2);