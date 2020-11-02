CREATE TABLE cars (id INT NOT NULL Primary Key AUTO_INCREMENT, make VARCHAR(16), model VARCHAR(16), colour VARCHAR(10),price DEC(10,2),VIN VARCHAR(20),dealership VARCHAR(25));

INSERT INTO cars (make,model,colour,price,VIN,dealership) VALUES 
('Benz','GLC300','Red',57000.00,'23423454','General Auto'),
('Benz','GLE500','Red',70000.00,'7687540','City Auto'),
('BMW','X7','Black',130000.00,'7823454','National Auto'),
('BMW','i8','Orange',180000.00,'8755658','General Auto'),
('Toyota','Land Cruiser','red',88000.00,'67545678','City Auto'),
('BMW','X5','Green',84000.00,'9809898','General Auto'),
('Audi','Q7','Gray',88000.00,'8786899','National Auto'),
('Tesla','Model S','Red',100000.00,'9896443','City Auto'),
('Tesla','Model X','Black',120000.50,'2343320','National Auto'),
('Tesla','Model Y','Blue',80000.00,'6545665','General Auto');


