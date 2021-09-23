create table breed (
	breedId int not null primary key auto_increment,
	breedName varchar(55)
);
/*=======================================*/
create table dogs (
	entryNum int not null primary key auto_increment,
	name varchar(55) NOT NULL,
	ownerName varchar(55),
	breedId int not null,
	gender varchar(15),
	classSpecialty varchar(55),
	constraint breed_fk foreign key (breedId)
  references breed (breedId)
);


create table SEC_USER
(
  userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userName         VARCHAR(36) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL 
) ;
/*=======================================*/
create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;
/*=======================================*/
create table USER_ROLE
(
  ID BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL,
  constraint USER_ROLE_UK unique (userId, roleId),
  constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId),
  constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId)
);