SELECT c.country_name, max(elevation) as highest_peak_elevation, max(length) as longest_river_length
FROM countries as c
LEFT JOIN mountains_countries as mc on mc.country_code = c.country_code
LEFT JOIN peaks as p on p.mountain_id = mc.mountain_id
LEFT JOIN countries_rivers as cr on cr.country_code = c.country_code
LEFT JOIN rivers as r on r.id = cr.river_id
GROUP BY mc.country_code, cr.country_code, country_name
ORDER BY max(elevation) DESC, max(length) DESC, country_name ASC
LIMIT 5;