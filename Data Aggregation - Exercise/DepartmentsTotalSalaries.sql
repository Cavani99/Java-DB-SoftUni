SELECT department_id, sum(salary) as "total_salary"
FROM employees
GROUP BY department_id
ORDER BY department_id;