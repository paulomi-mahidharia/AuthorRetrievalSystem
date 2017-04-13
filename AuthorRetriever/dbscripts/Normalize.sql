


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

-- Drop the table if exists 
DROP TABLE IF EXISTS `Author`;

-- Create a new table structure
CREATE TABLE Author(

Id int primary key auto_increment,
Name Varchar(100) NOT NULL UNIQUE
);

-- Generate Author table from Author Paper Mapping.
insert into Author(Name)
select distinct(RTRIM(LTRIM(name))) from  author_paper_mapping;

-- Insert into Author table for Author Article Mapping.
-- INSERT IGNORE ignores insert of duplicate keys.
insert ignore into Author(Name)
select (name) from author_article_mapping;

-- Add a new column for updating  Author_Id with the Author Table
ALTER TABLE author_paper_mapping 
ADD COLUMN Author_Id INT NOT NULL DEFAULT 0;

-- Add a new column for updating  Author_Id with the Author Table
ALTER TABLE author_article_mapping 
ADD COLUMN Author_Id INT NOT NULL DEFAULT 0;

-- Create index on name column for faster processing in author_paper_mapping table
CREATE INDEX name_index
ON author_paper_mapping (name);

-- Create index on name column for faster processing in author table
CREATE INDEX name_iauthor_ndex
ON author (name);

-- Create index on name column for faster processing in author_article_mapping table
CREATE INDEX name_index_author_article
ON author_article_mapping (name);

-- Update author_paper_mapping with the right author Id's
UPDATE author_paper_mapping apm join author au
on apm.name = au.name
SET apm.Author_Id = au.id
where apm.name = au.name;

-- Update author_article_mapping with the right author Id's
UPDATE author_article_mapping aam join author au 
on aam.name = au.name
SET aam.Author_Id = au.id
where aam.name = au.name;

insert ignore into author(Name) 
SELECT DISTINCT author FROM thesis;

ALTER TABLE committee 
ADD COLUMN conference_id INT NOT NULL;

update committee cm
join conference co
on cm.conf_name = co.conf_name and 
cm.year = co.year
set cm.conference_id = co.conf_id
where cm.conf_name = co.conf_name and 
cm.year = co.year;

-- Schema for editor
DROP TABLE IF EXISTS `editor`;

CREATE TABLE editor(
ID INT PRIMARY KEY AUTO_INCREMENT,
Name Varchar(100) NOT NULL,
position Varchar(200)
);

INSERT INTO editor(Name,position)
select editor, position from committee;

ALTER TABLE editor 
ADD COLUMN Author_Id INT NOT NULL;

UPDATE editor e join author a
on e.Name = a.Name
SET e.Author_id = a.Id
where e.Name = a.Name;


-- Schema for conference and editor mapping.
DROP TABLE IF EXISTS `conference_editor_mapping`;

CREATE TABLE conference_editor_mapping(
ID INT PRIMARY KEY AUTO_INCREMENT,
confId  INT NOT NULL,
editorId INT NOT NULL
);

INSERT INTO conference_editor_mapping(confId,editorId)
select c.conference_id,e.ID from committee c join editor e
on c.editor = e.Name
where  c.editor = e.Name;


-- Insert into conference from commitee table 
INSERT IGNORE INTO conference (conf_name,year) 
SELECT conf_name,year from committee;

-- Create a
CREATE INDEX name_index
ON committee (editor);

-- Create temporary table to find intersections of author and editor
CREATE TEMPORARY TABLE IF NOT EXISTS table2 AS (select a.name from committee c 
inner join author a on c.editor = a.Name);

-- Insert into authors table, data from the committee table that is not in temporary table.
Insert Ignore into Author (name)
SELECT c.editor
FROM committee c
LEFT JOIN table2 a
ON c.editor = a.Name
WHERE a.Name IS NULL;

-- Add a new column for updating  Author_Id with the Author Table
ALTER TABLE author_paper_mapping 
ADD COLUMN Paper_Id INT NOT NULL DEFAULT 0;

-- Create an index on commitee editor
CREATE INDEX paper_key_index
ON paper (paper_key);

-- Create an index on commitee editor
CREATE INDEX paper_key_index
ON author_paper_mapping (paper_key);

CREATE INDEX paper_id_index
ON author_paper_mapping (paper_id);

-- Author paper mapping update with the correct paper id
update author_paper_mapping apm
join paper p on apm.paper_key = p.paper_key
set apm.paper_id = p.paper_id
where apm.paper_key = p.paper_key;



-- Add column for conference name in paper
ALTER TABLE paper 
ADD COLUMN conference_name VARCHAR(200);

-- Set conference name from conference ( e.g. /conf/abc)
update paper 
set conference_name = SPLIT_STR(conference, '/', 2);



/*




DROP INDEX name_index ON author_paper_mapping;

DROP INDEX name_iauthor_ndex ON author;

DROP INDEX name_index_author_article ON author_article_mapping;

*/




 







