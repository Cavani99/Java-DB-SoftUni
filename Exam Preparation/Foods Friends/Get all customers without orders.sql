SELECT c.id,first_name,last_name
FROM customers as c
LEFT JOIN orders as o on o.customer_id = c.id
WHERE o.customer_id is null
ORDER BY c.id ASC;