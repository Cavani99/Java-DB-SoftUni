CREATE TABLE `people` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  `picture` MEDIUMBLOB NULL,
  `height` FLOAT(10,2) NULL,
  `weight` FLOAT(10,2) NULL,
   `gender` CHAR(1) NOT NULL,
	CONSTRAINT chk_gender CHECK (gender IN ('m', 'f')),
  `birthdate` DATE NOT NULL,
  `biography` LONGTEXT NULL,
  PRIMARY KEY(id));


INSERT INTO people (id, name, gender, birthdate)
VALUES
(1,'Sofia' , 'm', '1990-05-17'),
(2, 'Plovdiv', 'm', '1990-05-17'),
(3, 'Varna', 'm', '1990-05-17'),
(4, 'Ivan', 'm', '1990-05-17'),
(5, 'Georgi', 'm', '1990-05-17');
