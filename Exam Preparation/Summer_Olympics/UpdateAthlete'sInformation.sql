CREATE PROCEDURE udp_first_name_to_upper_case(letter char(1))
BEGIN
   UPDATE athletes
   SET first_name = upper(first_name)
   WHERE lower(right(first_name, 1)) = lower(letter);
END