CREATE TABLE `cities` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL UNIQUE
  );

    CREATE TABLE `cars` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	brand VARCHAR(20) NOT NULL,
	model VARCHAR(20) NOT NULL UNIQUE
    );

    CREATE TABLE `lessors` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL UNIQUE,
  company_employee_from DATE NOT NULL
);

    CREATE TABLE `rental_companies` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL UNIQUE,
  cross_border_usage BOOLEAN NOT NULL,
  price_per_day DECIMAL(10,2) NOT NULL,
  car_id INT NOT NULL,
	city_id INT NOT NULL,
  FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
  FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`));
  
  CREATE TABLE `renters` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL UNIQUE,
  age INT NOT NULL,
  phone_number VARCHAR(20) NOT NULL UNIQUE
  );


      CREATE TABLE `lessors_rental_companies` (
  lessor_id INT NOT NULL,
  rental_company_id INT NOT NULL,
  CONSTRAINT `fk_lessor_comp` FOREIGN KEY (lessor_id) REFERENCES `lessors` (id),
  CONSTRAINT `fk_rentalcomp`FOREIGN KEY (rental_company_id) REFERENCES `rental_companies` (id)
  );
  
        CREATE TABLE `lessors_renters` (
  lessor_id INT NOT NULL,
  renter_id INT NOT NULL,
  CONSTRAINT `fk_lessor_rent` FOREIGN KEY (lessor_id) REFERENCES `lessors` (id),
  CONSTRAINT `fk_rent`FOREIGN KEY (renter_id) REFERENCES `renters` (id)
  );