SELECT category_id, round(avg(price), 2)  as "Average Price",
round(min(price), 2)  as "Cheapest Product",
round(max(price), 2) as "Most Expensive Product"
from products
GROUP BY category_id;