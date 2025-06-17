SELECT e.employee_id, first_name, p.name as 'project_name'
FROM employees as e
JOIN employees_projects as ep on ep.employee_id = e.employee_id
JOIN projects as p on p.project_id = ep.project_id
WHERE date(start_date) > '2002-08-13' AND end_date is null
ORDER BY first_name ASC, p.name ASC
LIMIT 5;