CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
    SELECT  first_name, last_name FROM employees as e
	JOIN addresses as a on  a.address_id = e.address_id
	JOIN towns as t on a.town_id = t.town_id
	WHERE t.name = town_name
	ORDER BY first_name, last_name, employee_id ASC;
END