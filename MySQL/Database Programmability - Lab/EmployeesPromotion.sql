CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(20))
BEGIN
    UPDATE employees AS e
    JOIN departments AS d ON e.department_id = d.department_id
    SET e.salary = e.salary + e.salary * 0.05
    WHERE d.name = department_name;
END