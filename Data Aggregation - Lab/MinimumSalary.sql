SELECT department_id, round(min(salary), 2) as "Min Salary" from employees
GROUP BY department_id
HAVING min(salary) > 800
ORDER BY department_id;