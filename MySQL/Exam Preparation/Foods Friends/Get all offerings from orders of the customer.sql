SELECT o.id,o.name
FROM offerings as o
JOIN restaurants as r on r.id = o.restaurant_id
JOIN orders_offerings as orf on orf.restaurant_id = r.id
JOIN orders as ord on ord.id = orf.order_id
JOIN customers as c on c.id = ord.customer_id
WHERE o.vegan = 0 AND concat(first_name, ' ', last_name) = 'Sofia Sanchez'
GROUP BY o.id
ORDER BY o.id ASC;