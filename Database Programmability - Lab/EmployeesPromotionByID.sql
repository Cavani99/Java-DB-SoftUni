CREATE PROCEDURE usp_raise_salary_by_id(id int)
BEGIN
	DECLARE e_cnt int;
    SET e_cnt := (SELECT count(*) FROM employees WHERE employee_id = id);
    -- e_cnt = 1 => update; e_cnt = 0 => abort
	START TRANSACTION;
    UPDATE employees
    SET salary = salary * 1.05
    WHERE employee_id = id;
    
	IF (e_cnt = 0) THEN
    rollback;
    ELSE
    commit;
    END IF;
END