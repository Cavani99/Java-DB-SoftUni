UPDATE rental_companies as rc
JOIN cities as c on c.id = rc.city_id
SET price_per_day = price_per_day + 30
WHERE cross_border_usage = 1 and c.name = 'London';