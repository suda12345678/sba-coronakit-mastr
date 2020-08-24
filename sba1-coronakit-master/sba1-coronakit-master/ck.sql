-- This file contains the db script for
-- Inventory Management System Database

DROP DATABASE coronakitdb;

CREATE DATABASE coronakitdb;

USE coronakitdb;

CREATE TABLE user(
	name varchar(100) not null,
	email varchar(100) not null,
	username varchar(100) not null,
	password varchar(100) not null,
	contactnumber varchar(20),
	address varchar(200) not null
);


CREATE TABLE ckkit(
	id int primary key,
	personname varchar(20) not null,
	email varchar(20) not null,
	contactnumber varchar(10) not null,
	amount decimal not null,
	delvaddress varchar(20) not null,
	orderdate date,
	orderfinalised boolean default false
);

INSERT INTO ckkit VALUES
(1,"Sumathi","sumathigmail.com","123456789",1000,"flat no 20,vij","2020-02-01",false),
(2,"Sumathi","sumathigmail.com","123456789",1000,"flat no 20,hyd","2020-02-01",false),
(3,"Sumathi","sumathigmail.com","123456789",1000,"flat no 20,gun","2020-02-01",false);

CREATE TABLE kitdetail(
	kiddetailid int primary key,
	coronaKitid int not null,
	productid int not null,
	quantity int not null,
	amount decimal not null
);

INSERT INTO kitdetail VALUES
(1,2,3,10,1000);


CREATE TABLE products(
	productid int primary key,
	prodname varchar(10) not null,
	cost decimal not null,
	prodesc varchar(20) not null
);

INSERT INTO products VALUES
(1,"Face Mask",200,"comfy- breathable"),
(1,"Sanitiser",1500,"alchohol - 10%"),
(1,"glove5",500,"reusable");





