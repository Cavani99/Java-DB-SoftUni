SELECT o.name as offering_name , CASE 
    WHEN price <= 10 THEN 'cheap'
	 WHEN price > 10 AND price <= 25 THEN 'affordable'
    ELSE 'expensive'
  END AS "price_category"
FROM offerings as o
ORDER BY price DESC, o.name ASC;