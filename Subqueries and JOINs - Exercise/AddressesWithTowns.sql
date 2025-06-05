SELECT first_name, last_name, t.name as 'town', address_text
FROM employees as e
LEFT JOIN addresses as a on e.address_id = a.address_id
JOIN towns as t on t.town_id = a.town_id
ORDER BY first_name ASC, last_name ASC
LIMIT 5;