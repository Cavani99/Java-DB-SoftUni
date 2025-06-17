CREATE PROCEDURE udp_find_rental_company_by_car(brand varchar(20))
BEGIN
   SELECT rc.name, price_per_day
   FROM rental_companies as rc
   JOIN cars as c on c.id = rc.car_id
   WHERE c.brand = brand
   ORDER BY price_per_day DESC;
END