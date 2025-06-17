SELECT country_name, iso_code from countries
WHERE country_name REGEXP '^[^aA]*[aA][^aA]*[aA][^aA]*[aA]'
ORDER BY iso_code ASC;