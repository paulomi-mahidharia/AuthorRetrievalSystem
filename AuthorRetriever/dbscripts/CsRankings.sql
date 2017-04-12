
DROP TABLE IF EXISTS `author_faculty_affiliation`;

CREATE TABLE `author_faculty_affiliation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `affiliation` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `nameIndex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9097 DEFAULT CHARSET=utf8mb4;


CREATE INDEX nameIndex
ON author_faculty_affiliation (name);

ALTER TABLE author 
ADD COLUMN affiliation varchar(200) NOT NULL DEFAULT 'None';

update author a join author_faculty_affiliation afm
on a.name = afm.name
set a.affiliation = afm.affiliation
where a.name = afm.name;


