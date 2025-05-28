SELECT name, CASE 
    WHEN hour(start) >= 12 AND hour(start) < 18 THEN 'Afternoon'
	WHEN hour(start) >= 18 AND hour(start) < 24 THEN 'Evening'
    ELSE 'Morning'
  END AS "Part of the Day" 
,CASE 
    WHEN duration <= 3 THEN 'Extra Short'
	WHEN duration > 3 AND duration <= 6 THEN 'Short'
	WHEN duration > 6 AND duration <= 10 THEN 'Long'
    ELSE 'Extra Long'
  END AS "Duration" 
from games;