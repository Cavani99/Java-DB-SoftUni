SELECT deposit_group, max(magic_wand_size) as "longest_magic_wand"
from wizzard_deposits
GROUP BY deposit_group
ORDER BY max(magic_wand_size), deposit_group;