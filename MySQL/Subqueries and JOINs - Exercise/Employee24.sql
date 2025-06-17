SELECT e.employee_id, first_name, CASE 
    WHEN year(start_date) >= 2005 THEN null
    ELSE p.name
  END AS "project_name"
FROM employees as e
JOIN employees_projects as ep on ep.employee_id = e.employee_id
JOIN projects as p on p.project_id = ep.project_id
WHERE e.employee_id = 24
ORDER BY p.name ASC;