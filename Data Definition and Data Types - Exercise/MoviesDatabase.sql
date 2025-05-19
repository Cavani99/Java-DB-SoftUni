CREATE TABLE `directors` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  director_name VARCHAR(50) NOT NULL,
  `notes` LONGTEXT NULL,
  PRIMARY KEY(id));
  
  CREATE TABLE `genres` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  genre_name VARCHAR(50) NOT NULL,
  `notes` LONGTEXT NULL,
  PRIMARY KEY(id));
  
  CREATE TABLE `categories` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(50) NOT NULL,
  `notes` LONGTEXT NULL,
  PRIMARY KEY(id));
  
  CREATE TABLE `movies` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  `director_id` INT NULL,
  copyright_year DATE,
  length INT,
  `genre_id` INT NULL,
  `category_id` INT NULL,
  rating INT NULL,
  `notes` LONGTEXT NULL,
  PRIMARY KEY(id));


  INSERT INTO directors (id, director_name, notes)
VALUES
(1,'Ivan' , 'sasadsa'),
(2,'Ivan2' , 'sasadsa'),
(3,'Ivan3' , 'sasadsa'),
(4,'Ivan4' , 'sasadsa'),
(5,'Ivan5' , 'sasadsa');

INSERT INTO genres (id, genre_name, notes)
VALUES
(1,'Horror' , 'sasadsa'),
(2,'Horror2' , 'sasadsa'),
(3,'Horror3' , 'sasadsa'),
(4,'Horror4' , 'sasadsa'),
(5,'Horror5' , 'sasadsa');

INSERT INTO categories (id, category_name, notes)
VALUES
(1,'First' , 'sasadsa'),
(2,'Second' , 'sasadsa'),
(3,'Second' , 'sasadsa'),
(4,'Second' , 'sasadsa'),
(5,'Second' , 'sasadsa');

INSERT INTO movies (id, title, director_id, length, genre_id, category_id, rating, notes)
VALUES
(1,'First' , '1', 10, '2', '3', 9, 'sdada'),
(2,'First' , '2', 10, '5', '4', 7, 'sdada'),
(3,'First' , '3', 10, '4', '5', 4, 'sdada'),
(4,'First' , '4', 10, '3', '1', 3, 'sdada'),
(5,'First' , '5', 10, '1', '2', 1, 'sdada');