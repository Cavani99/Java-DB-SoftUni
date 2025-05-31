SELECT department_id, min(salary) as "minimum_salary"
from employees
WHERE hire_date > '2000-01-01' AND department_id in (2,5,7)
GROUP BY department_id
ORDER BY department_id ASC;