



create table scheduled_flights 
(
	id int not null auto_increment,
	airline varchar(20),
	source varchar(20) not null,
	destination varchar(20) not null,
	departure_date date not null,
	arrival_date date not null,
	cost float not null,
	seats int not null,
	available_seats int not null,
	PRIMARY KEY (id)
);
