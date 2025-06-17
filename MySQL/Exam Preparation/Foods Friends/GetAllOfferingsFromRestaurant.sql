SELECT o.name,price
FROM offerings as o
JOIN restaurants as r on r.id = o.restaurant_id
WHERE r.name = 'Burger Haven'
ORDER BY o.id ASC;