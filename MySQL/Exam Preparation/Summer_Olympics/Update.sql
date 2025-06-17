UPDATE disciplines
SET name = substring(name, 1, length(name) - 6)
WHERE name LIKE '%weight';