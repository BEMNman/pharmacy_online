CREATE DATABASE IF NOT EXISTS pharmacy DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS pharmacy.orders;

CREATE TABLE pharmacy.orders
(
    `id`           int(11)                            NOT NULL AUTO_INCREMENT,
    `creationDate` varchar(20)                        NOT NULL,
    `userId`       int(11)                            NOT NULL,
    `status`       enum ('CANCELED','PROCESS','PAID') NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_orders_users1_idx` (`userId`),
    CONSTRAINT `fk_orders_users1` FOREIGN KEY (`userId`) REFERENCES pharmacy.users (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;