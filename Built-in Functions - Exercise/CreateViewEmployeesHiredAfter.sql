CREATE VIEW `v_employees_hired_after_2000` AS
SELECT first_name, last_name from employees
WHERE year(hire_date) > 2000;

select * from `v_employees_hired_after_2000`;