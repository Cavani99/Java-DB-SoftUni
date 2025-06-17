SELECT first_name from employees
WHERE (department_id = 3 OR department_id = 10)
AND (year(hire_date) >= 1995 AND year(hire_date) <= 2005 )
ORDER BY employee_id;