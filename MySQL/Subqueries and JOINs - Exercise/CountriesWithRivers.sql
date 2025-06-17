SELECT country_name, river_name
FROM countries as c
LEFT JOIN countries_rivers as cr on cr.country_code = c.country_code
LEFT JOIN rivers as r on r.id = cr.river_id
JOIN continents as con on con.continent_code = c.continent_code
WHERE continent_name = 'Africa'
ORDER BY country_name ASC
LIMIT 5;