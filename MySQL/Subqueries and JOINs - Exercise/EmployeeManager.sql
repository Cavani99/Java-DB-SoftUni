SELECT e.employee_id, e.first_name, e.manager_id, m.`first_name` as manager_name
FROM employees as e
JOIN employees as m on m.employee_id = e.manager_id
WHERE e.manager_id = 3 OR e.manager_id = 7
ORDER BY e.first_name ASC;