ALTER TABLE Customers add column passwords varchar(20) after user_name;

ALTER TABLE Admins add column passwords varchar(20) after user_name;
   