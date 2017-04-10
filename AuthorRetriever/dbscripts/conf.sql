-- Schema for conference.
DROP TABLE IF EXISTS `conference`;

CREATE TABLE conference(
ID INT PRIMARY KEY AUTO_INCREMENT,
Name Varchar(100) NOT NULL,
title Varchar(200),
isbn varchar(200) ,
year int NOT NULL,
UNIQUE `unique_index`(`Name`, `year`)
);

ALTER TABLE committee 
ADD COLUMN conference_id INT NOT NULL;

update committee cm
join conference co
on cm.conf_name = co.Name and 
cm.year = co.year
set cm.conference_id = co.ID
where cm.conf_name = co.Name and 
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
INSERT IGNORE INTO conference (Name,year) 
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






