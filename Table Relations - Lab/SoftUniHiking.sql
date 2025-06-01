SELECT starting_point as route_starting_point, end_point as route_ending_point, leader_id,
concat(first_name, ' ', last_name) as leader_name
from routes as rt
JOIN campers on campers.id = rt.leader_id;