SELECT t.town_id, t.name as 'town_name', address_text
FROM addresses as a
LEFT JOIN towns as t on a.town_id = t.town_id
WHERE name = 'San Francisco' OR name = 'Sofia' OR name = 'Carnation'
ORDER BY t.town_id, address_id;
