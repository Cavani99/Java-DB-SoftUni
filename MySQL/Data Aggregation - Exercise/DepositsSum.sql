SELECT deposit_group, sum(deposit_amount) as "total_sum"
from wizzard_deposits
GROUP BY deposit_group
ORDER BY sum(deposit_amount);