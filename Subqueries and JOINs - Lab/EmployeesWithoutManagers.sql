SELECT e.employee_id, first_name, last_name, d.department_id, salary
FROM employees as e
LEFT JOIN departments as d on e.department_id = d.department_id
WHERE e.manager_id is null;