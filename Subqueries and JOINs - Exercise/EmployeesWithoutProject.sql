SELECT e.employee_id, first_name
FROM employees as e
LEFT JOIN employees_projects as p on p.employee_id = e.employee_id
WHERE p.employee_id is null
ORDER BY e.employee_id DESC
LIMIT 3;