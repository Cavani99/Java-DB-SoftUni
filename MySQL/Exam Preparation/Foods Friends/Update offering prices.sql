CREATE PROCEDURE udp_update_prices(restaurant_type varchar(40))
BEGIN
   UPDATE restaurants as r
   JOIN offerings as o on o.restaurant_id = r.id
   SET price = price + 5.00
   WHERE non_stop = 1 AND r.type = restaurant_type;
END