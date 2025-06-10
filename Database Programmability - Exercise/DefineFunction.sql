CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE i INT DEFAULT 1;
    DECLARE c CHAR(1);

	WHILE i  <= CHAR_LENGTH(word) DO
		SET c = substring(word, i, 1);
        IF instr(set_of_letters, c) = 0 THEN
         RETURN 0;
		END IF;
        SET i = i + 1;
    END WHILE;
    
    RETURN 1;
END