
  CREATE TABLE `categories` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  category VARCHAR(50) NOT NULL,
  daily_rate INT NULL,
  weekly_rate INT NULL,
  monthly_rate INT NULL,
  weekend_rate INT NULL,
  PRIMARY KEY(id));
  
    CREATE TABLE `cars` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  plate_number LONG NOT NULL,
  make VARCHAR(50) NULL,
  model VARCHAR(50) NULL,
  car_year DATE NULL,
  category_id INT NULL,
  doors INT NULL,
  picture MEDIUMBLOB,
  car_condition VARCHAR(50) NULL,
  available BOOLEAN,
  PRIMARY KEY(id));
  
	CREATE TABLE `employees` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  title VARCHAR(50),
  notes LONGTEXT NULL,
  PRIMARY KEY(id));
  
	CREATE TABLE `customers` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  driver_license_number LONG,
  full_name VARCHAR(100),
  address LONGTEXT,
  city VARCHAR(100),
  zip_code INT,
    notes LONGTEXT NULL,
  PRIMARY KEY(id));
  
 CREATE TABLE `rental_orders` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT,
  employee_id INT,
  customer_id INT,
  car_id INT,
  car_condition VARCHAR(50),
  tank_level INT,
  kilometrage_start INT,
  kilometrage_end INT,
  total_kilometrage INT,
  start_date DATE,
  end_date DATE,
  total_days INT,
  rate_applied INT,
  tax_rate INT,
  order_status VARCHAR(50), 
notes LONGTEXT NULL,
  PRIMARY KEY(id));

  INSERT INTO categories (id, category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
VALUES
(1,'Ivan' , 10, 20, 30, 40),
(2, 'Plovdiv', 10, 20, 30, 40),
(3, 'Varna', 10, 20, 30, 40);

  INSERT INTO cars (id, plate_number, make, model, category_id, doors, car_condition, available)
VALUES
(1,'1322141' , 'VW', 'Golf', '1', '4', 'fine', true),
(2, '1322141', 'VW', 'Golf', '2', '4', 'fine', true),
(3, '1322141', 'VW', 'Golf',  '3', '4', 'fine', true);

  INSERT INTO employees (id, first_name, last_name, title, notes)
VALUES
(1, 'Ivan', 'Ivanov', 'Good', 'dsaffa'),
(2, 'Ivan', 'Ivanov', 'Good', 'dsaffa'),
(3, 'Ivan', 'Ivanov', 'Good', 'dsaffa');

  INSERT INTO customers (id, driver_license_number, full_name, city, zip_code, notes)
VALUES
(1, '123141', 'Ivanov Stefan', 'Ruse', '4000', 'dsaffa'),
(2, '123142', 'Ivanov Stefan', 'Ruse', '4000', 'dsaffa'),
(3, '123143', 'Ivanov Stefan', 'Ruse', '4000', 'dsaffa');

  INSERT INTO rental_orders (id, employee_id, customer_id, car_id, car_condition, tank_level,
  kilometrage_start, kilometrage_end, total_kilometrage, total_days, rate_applied, tax_rate)
VALUES
(1, '1', '3', '2', 'good', 100, 20, 40, 60, 20, 10, 20),
(2, '2', '2','3', 'good', 100, 20, 40, 60, 20, 10, 20),
(3, '3', '1', '1', 'good', 100, 20, 40, 60, 20, 10, 20);