CREATE FUNCTION udf_total_medals_count_by_country(name varchar(40))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE e_count INT;

	SELECT count(dam.medal_id)
	INTO e_count
	FROM disciplines_athletes_medals as dam
	JOIN athletes as a on a.id = dam.athlete_id
	JOIN countries as c on c.id = a.country_id
	WHERE c.name = name;
    
     RETURN e_count;
END