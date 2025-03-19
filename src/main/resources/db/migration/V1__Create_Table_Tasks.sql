CREATE TABLE IF NOT EXISTS `tasks` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `status` enum('COMPLETED','IN_PROGRESS','PENDING') NOT NULL,
    `title` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
    );
