CREATE FUNCTION ufn_get_salary_level(salary decimal(10,4))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE e_count VARCHAR(50);
     IF (salary < 30000) THEN
		RETURN "Low";
	ELSEIF (salary >= 30000 AND salary <= 50000) THEN
		RETURN "Average";
	ELSE RETURN "High";
		END IF;
END