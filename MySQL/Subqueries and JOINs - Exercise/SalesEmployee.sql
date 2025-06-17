SELECT employee_id, first_name, last_name, d.name as department_name
FROM employees as e
LEFT JOIN departments as d on d.department_id = e.department_id
WHERE d.name = 'Sales'
ORDER BY employee_id DESC;