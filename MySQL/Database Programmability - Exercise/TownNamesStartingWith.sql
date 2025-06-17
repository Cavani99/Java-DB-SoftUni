CREATE PROCEDURE usp_get_towns_starting_with(text VARCHAR(50))
BEGIN
    SELECT  name as town_name FROM towns
	WHERE name LIKE CONCAT(text, '%')
	ORDER BY name ASC;
END