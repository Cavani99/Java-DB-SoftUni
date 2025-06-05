SELECT employee_id, first_name, salary, d.name as department_name
FROM employees as e
LEFT JOIN departments as d on d.department_id = e.department_id
WHERE salary > 15000
ORDER BY e.department_id DESC
LIMIT 5;