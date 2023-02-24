CREATE DATABASE LAUNDRY_MANAGEMENT;

USE LAUNDRY_MANAGEMENT;

CREATE TABLE USER(
    USER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    PHONE_NUMBER VARCHAR(15) NOT NULL,
    PASSWORD VARCHAR(64) NOT NULL,
    ADDRESS VARCHAR(256),
    ENABLED BIT NOT NULL,
    ROLE_ID INT NOT NULL,
    CREATED_BY INT,
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    UPDATED_BY INT
);

CREATE TABLE ROLE(
    ROLE_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ROLE_NAME VARCHAR(32) NOT NULL
);

CREATE TABLE STORE(
    STORE_ID INT NOT NULL PRIMARY KEY,
    STORE_NAME VARCHAR(128),
	ZALO VARCHAR(15),
    PHONE_NUMBER VARCHAR(15) NOT NULL,
    ADDRESS VARCHAR(128),
    CREATED_BY INT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_BY INT,
    UPDATED_AT TIMESTAMP
);

CREATE TABLE STORE_USER(
    STORE_USER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    STORE_ID INT NOT NULL,
    USER_ID INT NOT NULL,
    CREATED_AT TIMESTAMP
);

CREATE TABLE `ORDER`(
    ORDER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    STORE_ID INT NOT NULL,
    USER_ID INT NOT NULL,
    TOTAL_PRICE DECIMAL,
    ORDER_STATUS VARCHAR(16),
    CREATED_BY INT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_BY INT,
    UPDATED_AT TIMESTAMP
);

CREATE TABLE ORDER_ITEM(
    ORDER_ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ORDER_ID INT NOT NULL,
    ITEM VARCHAR(64) NOT NULL,
    ITEM_QUANTITY INT NOT NULL,
    PRICE INT NOT NULL,
    CREATED_BY INT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL
);

CREATE TABLE CHEMISTRY(
    CHEMISTRY_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SKU VARCHAR(24),
    CHEMISTRY_NAME VARCHAR(128) NOT NULL,
    CHEMISTRY_PRICE DECIMAL,
    CHEMISTRY_AMOUNT INT,
    IS_DELETED BIT,
    CREATED_BY INT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_BY INT NOT NULL,
    UPDATED_AT TIMESTAMP
);

CREATE TABLE ORDER_CHEMISTRY(
	ORDER_SCHEMISTRY_ID INT NOT NULL PRIMARY KEY,
    ORDER_ID INT NOT NULL,
    SCHEMISTRY_ID INT NOT NULL,
    ORDER_SCHEMISTRY_QUANTITY INT NOT NULL,
    CREATED_BY INT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL
);

CREATE TABLE ORDER_HISTORY(
        ORDER_HISTORY_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        ORDER_ID INT NOT  NULL,
        CHANGED_ITEM VARCHAR(64),
        CONTENT TEXT,
        CREATED_BY INT NOT NULL,
        CREATED_AT TIMESTAMP NOT NULL
);

CREATE TABLE MASTER_INFO(
        TYPE VARCHAR(64) NOT NULL,
        CD VARCHAR(64) NOT NULL,
        CD_NAME VARCHAR(256),
        SORT_ORDER SMALLINT(3),
        DETAIL VARCHAR(256),
        PRIMARY KEY (TYPE, CD)
);

CREATE TABLE CATEGORY(
    CATEGORY_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CATEGORY_NAME VARCHAR(32) NOT NULL,
	CATEGORY_PATH VARCHAR(128),
	CREATED_AT TIMESTAMP
);

CREATE TABLE CUSTOMER(
    CUSTOMER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CUSTOMER_NAME VARCHAR(128),
    PHONE_NUMBER VARCHAR(15) NOT NULL,
    PASSWORD VARCHAR(64) NOT NULL,
    ADDRESS VARCHAR(256),
    ENABLED BIT NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP
);

CREATE TABLE RECOMMENDER(
    RECOMMENDER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    RECOMMENDER_NAME VARCHAR(128),
    PHONE_NUMBER VARCHAR(15) NOT NULL,
    ADDRESS VARCHAR(256),
    NOTE TEXT,
    CREATED_BY INT NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL
);

INSERT INTO ROLE VALUES(1, "STORE_OWNER");
INSERT INTO ROLE VALUES(2, "STORE_STAFF");
