CREATE VIEW `v_employees_job_titles` AS
SELECT concat_ws(' ', `first_name`,`middle_name`,`last_name`) as full_name, job_title
FROM employees;