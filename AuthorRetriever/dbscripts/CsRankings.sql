
CREATE INDEX nameIndex
ON author_faculty_affiliation (name);

select * from author_faculty_affiliation afm 
join author a on a.name = afm.name
where a.name = afm.name;

ALTER TABLE author 
ADD COLUMN affiliation varchar(200);

update author a join author_faculty_affiliation afm
on a.name = afm.name
set a.affiliation = afm.affiliation
where a.name = afm.name;

select * from author;