-- TO DO
-- Paper Id in author paper mapping
-- Add column for conference name in paper
-- Make conference as conference_key



-- Add a new column for updating  Author_Id with the Author Table
ALTER TABLE author_paper_mapping 
ADD COLUMN Paper_Id INT NOT NULL DEFAULT 0;

-- Create an index on commitee editor
CREATE INDEX paper_key_index
ON paper (paper_key);

-- Create an index on commitee editor
CREATE INDEX paper_key_index
ON author_paper_mapping (paper_key);

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





