SELECT employee_id,concat(`first_name`, ' ', `last_name`) as full_name,
departments.department_id, departments.name as department_name
from employees 
RIGHT JOIN departments on departments.manager_id = employees.employee_id
ORDER BY employee_id ASC
LIMIT 5;