SELECT concat(first_name, ' ', last_name) as full_name, age
FROM athletes as a
LEFT JOIN disciplines_athletes_medals as dam on dam.athlete_id = a.id
ORDER BY age ASC,a.id ASC
LIMIT 2;