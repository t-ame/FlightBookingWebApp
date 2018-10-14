
 DROP TABLE IF EXISTS
    Admins;



create table Admins
(
	id int not null auto_increment,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	user_name varchar(25) not null unique key,
	address varchar(250),
	date_of_birth date,
	gender varchar(10),
	PRIMARY KEY (id)
);
