INSERT INTO offerings (name, price, vegan, restaurant_id)
SELECT concat(o.name, ' costs:'), o.price, o.vegan, o.restaurant_id
FROM offerings as o
WHERE o.name LIKE 'Grill%';