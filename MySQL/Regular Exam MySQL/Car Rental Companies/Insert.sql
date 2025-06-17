INSERT INTO renters (first_name,last_name,age,phone_number)
SELECT reverse(lower(first_name)), reverse(lower(last_name)), age + left(phone_number, 1),
concat('1+',phone_number)
FROM renters
WHERE age < 20;