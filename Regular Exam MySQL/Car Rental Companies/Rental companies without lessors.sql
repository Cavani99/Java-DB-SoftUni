SELECT rc.id,rc.name,c.brand
FROM rental_companies as rc
JOIN cars as c on c.id = rc.car_id
LEFT JOIN lessors_rental_companies as lrc on lrc.rental_company_id = rc.id
WHERE rental_company_id is null
ORDER BY c.brand ASC, rc.id ASC
LIMIT 5;