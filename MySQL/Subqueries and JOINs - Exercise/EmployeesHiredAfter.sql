SELECT first_name, last_name, hire_date, d.name as dept_name
FROM employees as e
JOIN departments as d on d.department_id = e.department_id
WHERE hire_date > '1999-01-01' AND (d.name = 'Sales' OR d.name = 'Finance')
ORDER BY hire_date ASC;