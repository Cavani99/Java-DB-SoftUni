SELECT a.id, a.first_name, a.last_name
FROM athletes as a
LEFT JOIN disciplines_athletes_medals as dam on dam.athlete_id = a.id
WHERE athlete_id is null
ORDER BY a.id ASC;