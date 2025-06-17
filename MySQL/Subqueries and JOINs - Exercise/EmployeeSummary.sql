SELECT e.employee_id, concat(e.first_name, ' ', e.last_name) as employee_name,
concat(m.first_name, ' ', m.last_name)  as manager_name, d.name as department_name
FROM employees as e
JOIN employees as m on m.employee_id = e.manager_id
JOIN departments as d on e.department_id = d.department_id
WHERE e.manager_id is not null
ORDER BY e.employee_id ASC
LIMIT 5;