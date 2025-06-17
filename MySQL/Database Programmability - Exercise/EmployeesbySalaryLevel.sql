CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(50))
BEGIN
    IF salary_level = 'low' THEN
        SELECT first_name, last_name 
        FROM employees 
        WHERE salary < 30000
        ORDER BY first_name DESC, last_name DESC;
        
    ELSEIF salary_level = 'average' THEN
        SELECT first_name, last_name 
        FROM employees 
        WHERE salary BETWEEN 30000 AND 50000
        ORDER BY first_name DESC, last_name DESC;
        
    ELSEIF salary_level = 'high' THEN
        SELECT first_name, last_name 
        FROM employees 
        WHERE salary > 50000
        ORDER BY first_name DESC, last_name DESC;
    END IF;
END