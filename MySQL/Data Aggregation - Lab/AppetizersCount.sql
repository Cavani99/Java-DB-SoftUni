SELECT count(id) as "count"
from products
WHERE category_id = 2 AND price > 8
GROUP BY category_id;