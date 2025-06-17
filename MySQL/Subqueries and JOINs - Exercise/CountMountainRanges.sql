SELECT mc.country_code, count(mc.mountain_id) as mountain_range
FROM mountains_countries as mc
JOIN countries as c on c.country_code = mc.country_code
JOIN mountains as m on m.id = mc.mountain_id
WHERE country_name = 'United States' OR country_name = 'Russia' OR country_name = 'Bulgaria'
GROUP BY mc.country_code
ORDER BY count(mc.mountain_id) DESC;