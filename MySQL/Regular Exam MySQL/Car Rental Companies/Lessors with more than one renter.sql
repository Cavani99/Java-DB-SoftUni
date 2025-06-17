SELECT l.first_name,l.last_name, count(renter_id) as renters_count, c.name
FROM lessors as l
JOIN lessors_renters as lr on lr.lessor_id = l.id
JOIN lessors_rental_companies as lrc on lrc.lessor_id = l.id
JOIN rental_companies as rc on rc.id = lrc.rental_company_id
JOIN cities as c on c.id = rc.city_id
GROUP BY l.id,c.name
HAVING count(renter_id) > 1
ORDER BY count(renter_id) DESC, l.first_name ASC;