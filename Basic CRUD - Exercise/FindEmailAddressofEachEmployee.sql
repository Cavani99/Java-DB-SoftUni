SELECT concat(`first_name`, '.' ,`last_name` , '@softuni.bg') AS full_email_address
FROM employees
ORDER BY employee_id ASC;