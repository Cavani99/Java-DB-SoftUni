SELECT avg(salary) as min_average_salary
FROM employees as e
JOIN departments as d on e.department_id = d.department_id
GROUP BY d.department_id
ORDER BY avg(salary) ASC
LIMIT 1;