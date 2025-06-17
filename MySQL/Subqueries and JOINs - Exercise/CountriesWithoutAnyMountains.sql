SELECT count(c.country_code) as country_count
FROM countries as c
LEFT JOIN mountains_countries as mc on mc.country_code = c.country_code
WHERE mc.country_code is null;