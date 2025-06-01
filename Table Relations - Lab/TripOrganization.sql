SELECT driver_id, vehicle_type, concat(first_name, ' ', last_name) as driver_name
from vehicles as vh
JOIN campers on campers.id = vh.driver_id;