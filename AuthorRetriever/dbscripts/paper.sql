DELIMITER $$
DROP FUNCTION IF EXISTS SPLIT_STR; $$
CREATE FUNCTION SPLIT_STR(
  x VARCHAR(255),
  delim VARCHAR(12),
  pos INT
)
RETURNS VARCHAR(255) DETERMINISTIC
BEGIN 
    RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1), delim, '');
END$$

DELIMITER ;

/*
select 	SPLIT_STR(conference, '/', 1) as memberfirst,
			SPLIT_STR(conference, '/', 2) as memberlast
FROM	paper 
where	conference like 'conf%'
and SPLIT_STR(conference, '/', 2) = 'ecoop'; */

-- Create Index on committee for joins later.
-- TO DO
-- Paper Id in author paper mapping
-- Add column for conference name in paper
-- Make conference as conference_key




