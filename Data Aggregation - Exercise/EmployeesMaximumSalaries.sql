SELECT department_id, max(salary) as "max_salary"
from employees
GROUP BY department_id
HAVING max(salary) > 70000 OR max(salary) < 30000
ORDER BY department_id ASC;