


create table booked_flights 
(
	id int not null auto_increment,
	airline varchar(20), 
	passenger_name varchar(50) not null,
	source varchar(20) not null, 
	destination varchar(20) not null,
	departure_time date not null, 
	arrival_time date not null,
	userId int not null,
	flightId int not null, 
	PRIMARY KEY (id),
	FOREIGN KEY (userId) REFERENCES Customers(id) 
	ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (flightId) REFERENCES scheduled_flights(id)
	ON DELETE CASCADE ON UPDATE NO ACTION
);

