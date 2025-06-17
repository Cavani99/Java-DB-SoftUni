SELECT concat(first_name, ' ', last_name) as full_name, age
FROM renters
WHERE first_name LIKE '%a%'
ORDER BY age ASC, id ASC
LIMIT 3;