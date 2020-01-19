CREATE DATABASE IF NOT EXISTS pharmacy DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS pharmacy.recipes;

CREATE TABLE pharmacy.recipes
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `creationDate` date    NOT NULL,
    `expDate`      date    NOT NULL,
    `medicamentId` int(11) NOT NULL,
    `pacientId`    int(11) NOT NULL,
    `doctorId`     int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_recipes_users1_idx` (`pacientId`),
    KEY `fk_recipes_users2_idx` (`doctorId`),
    KEY `fk_recipes_medicines1_idx` (`medicamentId`),
    CONSTRAINT `fk_recipes_medicines1` FOREIGN KEY (`medicamentId`) REFERENCES pharmacy.medicines (`id`),
    CONSTRAINT `fk_recipes_users1` FOREIGN KEY (`pacientId`) REFERENCES pharmacy.users (`id`),
    CONSTRAINT `fk_recipes_users2` FOREIGN KEY (`doctorId`) REFERENCES pharmacy.users (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
