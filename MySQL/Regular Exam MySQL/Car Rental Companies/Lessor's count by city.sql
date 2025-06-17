SELECT c.name, count(lessor_id) as lessors_count
FROM cities as c
JOIN rental_companies as rc on rc.city_id = c.id
JOIN lessors_rental_companies as lrc on lrc.rental_company_id = rc.id
JOIN lessors as l on l.id = lrc.lessor_id
GROUP BY c.id
HAVING count(lessor_id) > 0
ORDER BY count(lessor_id) DESC, c.name ASC;