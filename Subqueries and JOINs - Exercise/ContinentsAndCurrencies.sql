WITH currency_usage_per_continent AS (
    SELECT 
        con.continent_code,
        c.currency_code,
        COUNT(*) AS currency_usage,
        DENSE_RANK() OVER (
            PARTITION BY con.continent_code
            ORDER BY COUNT(*) DESC
        ) AS rank_in_continent
    FROM countries AS c
    JOIN continents AS con ON con.continent_code = c.continent_code
    JOIN currencies AS curr ON curr.currency_code = c.currency_code
    GROUP BY con.continent_code, c.currency_code
    HAVING currency_usage > 1
)
SELECT 
    continent_code,
    currency_code,
    currency_usage
FROM currency_usage_per_continent
WHERE rank_in_continent = 1
ORDER BY continent_code ASC, currency_code ASC;