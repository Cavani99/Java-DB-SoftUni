SELECT country_name, country_code, CASE 
    WHEN currency_code = 'EUR' THEN 'Euro'
    ELSE 'Not Euro'
  END AS currency FROM countries
WHERE continent_code = "EU"
ORDER BY country_name ASC;