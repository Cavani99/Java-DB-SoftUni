SELECT substring(first_name, 1, 1) as "first_letter"
from wizzard_deposits
where deposit_group = "Troll Chest"
GROUP BY substring(first_name, 1, 1)
ORDER BY substring(first_name, 1, 1) ASC;