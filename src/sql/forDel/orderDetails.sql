CREATE DATABASE IF NOT EXISTS pharmacy DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS pharmacy.orderDetails;

CREATE TABLE pharmacy.orderDetails
(
    `id`           int(11)        NOT NULL AUTO_INCREMENT,
    `orderId`      int(11)        NOT NULL,
    `medicamentId` int(11)        NOT NULL,
    `amount`       int(11)        NOT NULL,
    `totalPrice`   decimal(10, 3) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_order_details_orders1_idx` (`orderId`),
    KEY `fk_order_details_medicines1_idx` (`medicamentId`),
    CONSTRAINT `fk_order_details_medicines1` FOREIGN KEY (`medicamentId`) REFERENCES pharmacy.medicines (`id`),
    CONSTRAINT `fk_order_details_orders1` FOREIGN KEY (`orderId`) REFERENCES pharmacy.orders (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
