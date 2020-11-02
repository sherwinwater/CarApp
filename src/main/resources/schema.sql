DROP TABLE if exists cars;
CREATE TABLE cars (id SERIAL NOT NULL Primary Key, make VARCHAR(16), model VARCHAR(16), colour VARCHAR(10),price DEC(10,2),VIN VARCHAR(20),dealership VARCHAR(25));
--CREATE TABLE cars (id INT NOT NULL Primary Key AUTO_INCREMENT, make VARCHAR(16), model VARCHAR(16), colour VARCHAR(10),price DEC(10,2),VIN VARCHAR(20),dealership VARCHAR(25));