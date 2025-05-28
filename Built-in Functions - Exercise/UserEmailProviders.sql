SELECT user_name, substring_index(email, '@', -1) as "email provider" from users
ORDER BY substring_index(email, '@', -1), user_name;