
DROP TABLE IF EXISTS conference;
CREATE TABLE conference (
	conf_id INT AUTO_INCREMENT PRIMARY KEY,
	conf_name varchar(200) DEFAULT NULL,
	`year` int(10) DEFAULT '0',
	title text,
	book_title text,
    isbn text,
	url text,
    UNIQUE KEY (`conf_name`,`year`)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS journal;
CREATE TABLE journal (
	journal_id INT AUTO_INCREMENT PRIMARY KEY,
	journal_name varchar(200) DEFAULT NULL,
	`year` int(10) DEFAULT '0',
	title text,
	book_title text,
    isbn text,
	url text,
    UNIQUE KEY (`journal_name`,`year`)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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


select 	SPLIT_STR(proceeding_key, '/', 1) as memberfirst,
			SPLIT_STR(proceeding_key, '/', 2) as memberlast
FROM	proceeding 
where	proceeding_key like 'conf%';




insert ignore into conference(conf_name, `year`, title, book_title, isbn, url)
	select	SPLIT_STR(proceeding_key, '/', 2) as conf_name,
                `year`,
                title,
                book_title,
                isbn,
                url
	FROM proceeding
    WHERE	proceeding_key like 'conf%';

    
insert ignore into journal(journal_name, `year`, title, book_title, isbn, url)
	select	SPLIT_STR(proceeding_key, '/', 2) as journal_name,
                `year`,
                title,
                book_title,
                isbn,
                url
	FROM proceeding
    WHERE	proceeding_key like 'journal%';
		
commit;