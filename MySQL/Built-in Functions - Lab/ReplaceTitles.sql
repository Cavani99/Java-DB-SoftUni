SELECT replace(title, "The", "***") as title from books
WHERE substring(title, 1, 3) = "The"
ORDER BY id;