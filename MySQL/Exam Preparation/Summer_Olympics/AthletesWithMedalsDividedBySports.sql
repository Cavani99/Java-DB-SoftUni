SELECT a.id, a.first_name, a.last_name, count(medal_id) as medals_count , s.name as sport
FROM athletes as a
LEFT JOIN disciplines_athletes_medals as dam on dam.athlete_id = a.id
JOIN disciplines as d on d.id = dam.discipline_id
JOIN sports as s on s.id = d.sport_id
GROUP BY a.id, s.name, a.first_name
ORDER BY count(medal_id) DESC, a.first_name ASC
LIMIT 10;