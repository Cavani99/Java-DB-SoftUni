SELECT `name`, date_format(start, "%Y-%m-%d") as start from games
WHERE year(`start`) = 2011 OR year(`start`) = 2012
ORDER BY `start`, `name`
LIMIT 50;