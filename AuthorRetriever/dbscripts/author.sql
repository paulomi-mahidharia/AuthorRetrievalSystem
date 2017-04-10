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

/*

DROP INDEX name_index ON author_paper_mapping;

DROP INDEX name_iauthor_ndex ON author;

DROP INDEX name_index_author_article ON author_article_mapping;

*/




 







