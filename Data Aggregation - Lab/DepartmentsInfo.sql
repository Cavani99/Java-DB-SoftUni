SELECT department_id, count(id) as "Number of employees" from employees
GROUP BY department_id
ORDER BY department_id, count(id);