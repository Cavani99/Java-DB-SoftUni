  CREATE TABLE `restaurants` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL UNIQUE,
  type VARCHAR(20) NOT NULL,
  non_stop BOOLEAN NOT NULL
  );

    CREATE TABLE `offerings` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL UNIQUE,
	price DECIMAL(19,2) NOT NULL,
    vegan BOOLEAN NOT NULL,
    restaurant_id INT NOT NULL,
  FOREIGN KEY (`restaurant_id`)
  REFERENCES `restaurants` (`id`)
  );

    CREATE TABLE `customers` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,
  phone_number VARCHAR(20) NOT NULL UNIQUE,
  regular BOOLEAN NOT NULL,
  CONSTRAINT UNIQUE (`first_name`, `last_name`)
);

    CREATE TABLE `orders` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  number VARCHAR(10) NOT NULL UNIQUE,
  priority VARCHAR(10) NOT NULL,
  customer_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`));


      CREATE TABLE `orders_offerings` (
  order_id INT NOT NULL,
  offering_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  PRIMARY KEY (order_id,offering_id),
  CONSTRAINT `fk_order_res` FOREIGN KEY (restaurant_id, order_id) REFERENCES `orders` (restaurant_id, id),
  CONSTRAINT `fk_offering_res`FOREIGN KEY (restaurant_id, offering_id) REFERENCES `offerings` (restaurant_id, id)
  );