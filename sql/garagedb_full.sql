CREATE DATABASE garagedb;
USE garagedb;

CREATE TABLE customer(
    customer_id VARCHAR(100) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    Engine_SerialNumber VARCHAR(100),
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE employee(
    emp_id VARCHAR(100) NOT NULL,
    emp_name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (emp_id)
);

CREATE TABLE car(
    Engine_SerialNumber VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price INTEGER NOT NULL,
    buyer VARCHAR(100),
    CONSTRAINT pk_car PRIMARY KEY (Engine_SerialNumber)
);

CREATE TABLE owner(
    owner_id VARCHAR(100) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_owner PRIMARY KEY (owner_id)
);

INSERT INTO owner VALUES('1','jainav');

INSERT INTO employee VALUES('e1','aditya');

INSERT INTO customer VALUES('1', 'Rahul', NULL);
INSERT INTO customer VALUES('2', 'Abdul', NULL);
INSERT INTO customer VALUES('3', 'Arya', NULL);
INSERT INTO customer VALUES('4', 'Rakshit', NULL);

INSERT INTO car VALUES('A1000', 'Ford', 'Mustang',7461000, NULL);
INSERT INTO car VALUES('B2001', 'Tesla', 'Model S',15000000, NULL);
INSERT INTO car VALUES('C3002', 'Range Rover', 'Range Rover Sport S',9126527,NULL);





