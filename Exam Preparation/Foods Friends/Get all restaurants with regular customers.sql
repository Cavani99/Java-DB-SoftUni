SELECT r.id,r.name
FROM restaurants as r
JOIN offerings as o on r.id = o.restaurant_id
JOIN orders as ord on r.id = ord.restaurant_id
JOIN customers as c on c.id = ord.customer_id
WHERE o.vegan = 1 AND c.regular = 1 AND ord.priority = 'HIGH'
GROUP BY r.id
ORDER BY r.id ASC;