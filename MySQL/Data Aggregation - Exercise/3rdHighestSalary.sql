 SELECT e.`department_id`, (
	SELECT DISTINCT e1.`salary` 
    FROM `employees` AS e1
    WHERE e1.`department_id` = e.`department_id`
    ORDER BY e1.`salary` DESC
    LIMIT 1 OFFSET 2
 ) AS `ths`
 FROM `employees` AS e
 GROUP BY e.`department_id`
 HAVING `ths` IS NOT NULL
 ORDER BY e.`department_id`;