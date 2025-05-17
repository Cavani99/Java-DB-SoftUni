CREATE TABLE `users` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) UNIQUE NOT NULL,
  `password` VARCHAR(26) NOT NULL,
  `profile_picture` MEDIUMBLOB,
  `last_login_time` TIME,
   `is_deleted` BOOLEAN,
  PRIMARY KEY(id));
  
  INSERT INTO users (id, username, password, is_deleted)
VALUES
(1,'Sofia' , '1322141', 0),
(2, 'Plovdiv', '1322141', 0),
(3, 'Varna', '1322141', 0),
(4, 'Ivan', '1322141',  0),
(5, 'Georgi', '1322141', 0);