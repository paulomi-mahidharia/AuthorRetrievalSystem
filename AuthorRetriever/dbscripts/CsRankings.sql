
# Affiliate University Information
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


# Homepage
DROP TABLE IF EXISTS `author_homepage`;

CREATE TABLE `author_homepage` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `url` varchar(400) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `homepageNameIndex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9097 DEFAULT CHARSET=utf8mb4;


CREATE INDEX homepageNameIndex
ON author_homepage (name);




# Now add records to author table

ALTER TABLE author 
ADD COLUMN affiliation varchar(200) NOT NULL DEFAULT 'None';

update author a join author_faculty_affiliation afm
on a.name = afm.name
set a.affiliation = afm.affiliation
where a.name = afm.name;


ALTER TABLE author 
ADD COLUMN url varchar(400) NOT NULL DEFAULT 'None';

update author a join author_faculty_affiliation afm
on a.name = afm.name
set a.affiliation = afm.affiliation
where a.name = afm.name;


