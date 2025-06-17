INSERT INTO athletes (first_name, last_name, age, country_id)
SELECT upper(a.first_name),
concat(a.last_name, ' comes from ', c.name), a.age + c.id, a.country_id
FROM athletes as a
JOIN countries as c on c.id = a.country_id
WHERE c.name LIKE 'A%';