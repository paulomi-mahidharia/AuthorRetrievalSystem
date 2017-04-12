CREATE DATABASE IF NOT EXISTS db_dblp;
USE db_dblp;

DROP TABLE IF EXISTS `author_paper_mapping`;
CREATE TABLE `author_paper_mapping` (
  `name` varchar(100) DEFAULT NULL,
  `paper_key` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `paper`;
CREATE TABLE paper (
  paper_id INT AUTO_INCREMENT PRIMARY KEY,
  paper_key varchar(200) DEFAULT NULL,
  title text,
  book_title text,
  `year` int(11) DEFAULT '0',
  `conference` text,
  url text
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS `author_article_mapping`;
CREATE TABLE `author_article_mapping` (
  `name` varchar(100) DEFAULT NULL,
  `article_key` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `article`;
CREATE TABLE article (
  article_id INT AUTO_INCREMENT PRIMARY KEY,
  article_key varchar(200) DEFAULT NULL,
  title text,
  `year` int(11) DEFAULT '0',
  journal text,
  url text,
  conference text
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




DROP TABLE IF EXISTS `editor_proceeding_mapping`;
CREATE TABLE `editor_proceeding_mapping` (
  `name` varchar(100) DEFAULT NULL,
  `proceeding_key` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `proceeding`;
CREATE TABLE proceeding (
  proceeding_id INT AUTO_INCREMENT PRIMARY KEY,
  proceeding_key varchar(200) DEFAULT NULL,
  title text,
  book_title text,
  isbn text,
  `year` int(11) DEFAULT '0',
  url text
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `thesis`;
CREATE TABLE thesis (
  thesis_id INT AUTO_INCREMENT PRIMARY KEY,
  thesis_key varchar(200) DEFAULT NULL,
  author text,
  title text,
  school text,
  `year` int(11) DEFAULT '0',
  url text,
  `type` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS `committee`;
CREATE TABLE committee (
  id INT AUTO_INCREMENT PRIMARY KEY,
  conf_name varchar(200) DEFAULT NULL,
  `year` int(11) DEFAULT '0',
  editor varchar(200),
  `position` varchar(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `author_alias`;
CREATE TABLE author_alias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  alias_name text,
  author_name text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;