SELECT concat(a.first_name, ' ', a.last_name) as full_name, CASE 
    WHEN age <= 18 THEN 'Teenager'
	 WHEN age > 18 AND age <= 25 THEN 'Young adult'
    ELSE 'Adult'
  END AS "age_group" 
FROM athletes as a
ORDER BY age DESC, a.first_name ASC;