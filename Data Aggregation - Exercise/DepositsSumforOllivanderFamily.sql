SELECT deposit_group, sum(deposit_amount) as "total_sum"
from wizzard_deposits
WHERE magic_wand_creator = "Ollivander family"
GROUP BY deposit_group
ORDER BY deposit_group;