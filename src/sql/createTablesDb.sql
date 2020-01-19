DROP DATABASE pharmacy;
CREATE DATABASE pharmacy DEFAULT CHARACTER SET utf8;

DROP table if exists pharmacy.users;
CREATE TABLE pharmacy.users
(
    `id`       int(11)                                  NOT NULL AUTO_INCREMENT,
    `name`     varchar(45)                              NOT NULL,
    `login`    varchar(45)                              NOT NULL UNIQUE,
    `password` varchar(45)                              NOT NULL,
    `role`     enum ('PACIENT', 'DOCTOR', 'PHARMACIST') NOT NULL,
    `locked`   boolean DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS pharmacy.medicines;
CREATE TABLE pharmacy.medicines
(
    `id`           int(11)                                             NOT NULL AUTO_INCREMENT,
    `name`         varchar(45)                                         NOT NULL,
    `form`         enum ('PILL', 'SOLUTION', 'POWDER', 'CREAM', 'GEL') NOT NULL,
    `dosage`       varchar(10),
    `recipe`       boolean DEFAULT '0',
    `amountInPack` int(11) DEFAULT '0',
    `price`        decimal(10, 2),
    `quantity`     int(11) DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS pharmacy.orders;
CREATE TABLE pharmacy.orders
(
    `id`           int(11)                            NOT NULL AUTO_INCREMENT,
    `creationDate` datetime                           NOT NULL,
    `userId`       int(11)                            NOT NULL,
    `price`        decimal(10, 2)                     NOT NULL,
    `status`       enum ('CANCELED','PROCESS','PAID') NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_orders_users1_idx` (`userId`),
    CONSTRAINT `fk_orders_users1` FOREIGN KEY (`userId`) REFERENCES pharmacy.users (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS pharmacy.orderDetails;
CREATE TABLE pharmacy.orderDetails
(
    `id`           int(11)        NOT NULL AUTO_INCREMENT,
    `orderId`      int(11)        NOT NULL,
    `medicamentId` int(11)        NOT NULL,
    `quantity`     int(11)        NOT NULL,
    `price`        decimal(10, 2) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_order_details_orders1_idx` (`orderId`),
    KEY `fk_order_details_medicines1_idx` (`medicamentId`),
    CONSTRAINT `fk_order_details_medicines1` FOREIGN KEY (`medicamentId`) REFERENCES pharmacy.medicines (`id`),
    CONSTRAINT `fk_order_details_orders1` FOREIGN KEY (`orderId`) REFERENCES pharmacy.orders (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;