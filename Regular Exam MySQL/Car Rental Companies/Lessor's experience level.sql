SELECT concat(first_name, ' ', last_name) as full_name, CASE 
    WHEN year(company_employee_from) >= 1980 AND year(company_employee_from) < 1990 THEN 'Specialist'
	  WHEN year(company_employee_from) >= 1990 AND year(company_employee_from) < 2000 THEN 'Advanced'
      WHEN year(company_employee_from) >= 2000 AND year(company_employee_from) < 2008 THEN 'Experienced'
      WHEN year(company_employee_from) >= 2008 AND year(company_employee_from) < 2015 THEN 'Qualified'
      WHEN year(company_employee_from) >= 2015 AND year(company_employee_from) < 2020 THEN 'Provisional'
    ELSE 'Trainee'
  END AS "level"
FROM lessors as l
ORDER BY year(company_employee_from) ASC, first_name ASC;