CREATE FUNCTION udf_get_offerings_average_price_per_restaurant(restaurant_name varchar(40))
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
	DECLARE e_count DECIMAL(10,2);

	SELECT avg(o.price)
	INTO e_count
	FROM offerings as o
	JOIN restaurants as r on r.id = o.restaurant_id
	WHERE r.name = restaurant_name;
    
     RETURN e_count;
END