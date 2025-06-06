SELECT c.country_code, mountain_range, peak_name, elevation
FROM peaks as p
JOIN mountains_countries as mc on mc.mountain_id = p.mountain_id
JOIN countries as c on c.country_code = mc.country_code
JOIN mountains as m on m.id = p.mountain_id
WHERE country_name = 'Bulgaria' AND elevation > 2835
ORDER BY elevation DESC;