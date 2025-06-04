SELECT count(e.employee_id) as 'count'
FROM employees as e
WHERE e.salary > (
	SELECT avg(salary) from employees
);