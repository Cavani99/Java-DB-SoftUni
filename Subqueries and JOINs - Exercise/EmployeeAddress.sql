SELECT employee_id, job_title, e.address_id, address_text
FROM addresses as a
RIGHT JOIN employees as e on e.address_id = a.address_id
ORDER BY address_id ASC
LIMIT 5;