SELECT peak_name, river_name, concat(lower(peak_name), lower(substring(river_name, 2))) as mix from peaks
INNER JOIN rivers ON right(peak_name, 1) = left(river_name, 1)
ORDER BY mix;