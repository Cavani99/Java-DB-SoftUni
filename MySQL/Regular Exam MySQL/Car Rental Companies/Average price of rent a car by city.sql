CREATE FUNCTION udf_average_price_by_city(name varchar(40))
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
	DECLARE e_count DECIMAL(10,2);

	SELECT avg(price_per_day) as average_price_per_day
	INTO e_count
	FROM cities as c
	JOIN rental_companies as rc on rc.city_id = c.id
	WHERE c.name = name;
    
     RETURN e_count;
END