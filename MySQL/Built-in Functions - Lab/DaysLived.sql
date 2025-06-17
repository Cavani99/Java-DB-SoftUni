SELECT concat_ws(' ', `first_name`,`last_name`) as `Full Name`, timestampdiff(day, born, died) as `Days Lived`
from authors;