INSERT INTO breed (breedName) VALUES 
('Labrador Retrievers'),('German Shepherd'),('Golden Retrievers'),('French Bulldogs'),
('Bulldogs'),('Beagles'),('Poodles'),('Rottweilers'),('Yorkshire Terriers'),('Boxers'),
('Dachshunds'),('Great Danes')
;

INSERT INTO dogs (name, ownerName, breedId,gender,classSpecialty) VALUES 
('Charlie', 'Chris', 1,'Female','Class'),
('Max', 'Mark', 2,'Male','Specialty'),
('Buddy', 'David', 3,'Female','Class'),
('Oscar', 'Jim', 4,'Male','Specialty'),
('Milo', 'Jhon', 11,'Female','Class'),
('Ollie', 'Tod', 2,'Male','Specialty'),
('Jack', 'Ferhad', 11,'Female','Class'),
('Teddy', 'Karl', 3,'Male','Specialty'),
('Molly', 'Charli', 4,'Female','Class'),
('Coco', 'Adam', 5,'Male','Specialty'),
('Bella', 'William', 6,'Female','Class'),
('Bailey', 'James', 6,'Male','Specialty'),
('Teddy', 'Harper', 4,'Female','Class'),
('Frankie', 'Mason', 5,'Male','Specialty'),
('Lola', 'Ella', 1,'Female','Class'),
('Buddy', 'Avery', 12,'Male','Specialty'),
('Milo', 'Ferhad', 11,'Female','Class'),
('Ollie', 'Karl', 3,'Male','Specialty'),
('Oscar', 'Charli', 4,'Female','Class'),
('Frankie', 'Adam', 5,'Male','Specialty'),
('Frankie', 'William', 6,'Female','Class'),
('Ollie', 'James', 6,'Male','Specialty'),
('Milo', 'Harper', 4,'Female','Class'),
('Teddy', 'Mason', 5,'Male','Specialty'),
('Oscar', 'Ella', 8,'Female','Class'),
('Bella', 'Avery', 12,'Male','Specialty'),
('Lola', 'Ferhad', 11,'Female','Class'),
('Bailey', 'Karl', 3,'Male','Specialty'),
('Buddy', 'Charli', 4,'Female','Class'),
('Coco', 'Adam', 5,'Male','Specialty'),
('Charlie', 'William', 6,'Female','Class'),
('Molly', 'James', 8,'Male','Specialty'),
('Bailey', 'Harper', 4,'Female','Class'),
('Milo', 'Mason', 5,'Male','Specialty'),
('Ollie', 'Ella', 1,'Female','Class'),
('Bailey', 'Avery', 12,'Male','Specialty'),
('Teddy', 'Chris', 1,'Female','Class'),
('Milo', 'Mark', 2,'Male','Specialty'),
('Oscar', 'David', 3,'Female','Class'),
('Bella', 'Jim', 4,'Male','Specialty'),
('Teddy', 'Jhon', 11,'Female','Class'),
('Frankie', 'Tod', 7,'Male','Specialty'),
('Lola', 'Ferhad', 11,'Female','Class'),
('Buddy', 'Karl', 3,'Male','Specialty'),
('Molly', 'Charli', 9,'Female','Class'),
('Bailey', 'Adam', 5,'Male','Specialty'),
('Teddy', 'William', 6,'Female','Class'),
('Ollie', 'James', 6,'Male','Specialty'),
('Milo', 'Harper', 9,'Female','Class'),
('Coco', 'Mason', 5,'Male','Specialty'),
('Oscar', 'Ella', 1,'Female','Class'),
('Charlie', 'Avery', 12,'Male','Specialty'),
('Teddy', 'Chris', 1,'Female','Class'),
('Lola', 'Mark', 2,'Male','Specialty'),
('Molly', 'David', 3,'Female','Class'),
('Teddy', 'Jim', 4,'Male','Specialty'),
('Frankie', 'Jhon', 11,'Female','Class'),
('Buddy', 'Tod', 2,'Male','Specialty'),
('Bailey', 'Ferhad', 7,'Female','Class'),
('Lola', 'Karl', 10,'Male','Specialty'),
('Teddy', 'Charli', 4,'Female','Class'),
('Milo', 'Adam', 5,'Male','Specialty'),
('Teddy', 'William', 10,'Female','Class'),
('Molly', 'James', 6,'Male','Specialty'),
('Coco', 'Harper', 4,'Female','Class'),
('Frankie', 'Mason', 5,'Male','Specialty'),
('Lola', 'Ella', 1,'Female','Class'),
('Charlie', 'Avery', 12,'Male','Specialty')
;

/*=====================================================*/
insert into SEC_User (userName, encryptedPassword, ENABLED)
values  ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1),
 	    ('Chris', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1),
 		('Tom', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
/*=====================================================*/
insert into sec_role (roleName) values 
	('ROLE_ADMIN'),
	('ROLE_OWNER');
/*=====================================================*/
insert into user_role (userId, roleId) values 
	(1, 1),
	(2, 2),
	(3, 2);

 