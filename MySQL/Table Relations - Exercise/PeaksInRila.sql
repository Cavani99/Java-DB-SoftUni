SELECT mountain_range, peak_name, elevation as peak_elevation
from peaks
JOIN mountains on mountain_id = mountains.id
WHERE mountain_range = "Rila"
ORDER BY peak_elevation DESC;