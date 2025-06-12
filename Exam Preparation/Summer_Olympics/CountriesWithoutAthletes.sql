SELECT c.id, c.name
FROM countries as c
LEFT JOIN athletes as a on a.country_id = c.id
WHERE a.country_id is null
GROUP BY c.id
ORDER BY c.name DESC
LIMIT 15;