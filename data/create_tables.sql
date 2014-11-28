--
-- Table structure for table `actors`
--
DROP TABLE IF EXISTS `actors`;
CREATE TABLE `actors` (
  `id` int(11) NOT NULL DEFAULT '0',
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `film_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `directors`
--
DROP TABLE IF EXISTS `directors`;
CREATE TABLE `directors` (
  `id` int(11) NOT NULL DEFAULT '0',
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `directors_genres`
--
DROP TABLE IF EXISTS `directors_genres`;
CREATE TABLE `directors_genres` (
  `director_id` int(11) DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `prob` float DEFAULT NULL,
  KEY `idx_director_id` (`director_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `movies`
--
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `rank` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `movies_directors`
--
DROP TABLE IF EXISTS `movies_directors`;
CREATE TABLE `movies_directors` (
  `director_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  KEY `idx_director_id` (`director_id`),
  KEY `idx_movie_id` (`movie_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `movies_genres`
--
DROP TABLE IF EXISTS `movies_genres`;
CREATE TABLE `movies_genres` (
  `movie_id` int(11) DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- Table structure for table `roles`
--
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `actor_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  KEY `idx_actor_id` (`actor_id`),
  KEY `idx_movie_id` (`movie_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;