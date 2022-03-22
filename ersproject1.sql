
-- Creating a table to keep track of reimbursement status 
create table ERS_REIMBURSEMENT_STATUS (
	REIMB_STATUS_ID int primary key unique,
	REIMB_STATUS varchar(10) not null unique
);

-- Creating a table to keep track of reimbursement type
create table ERS_REIMBURSEMENT_TYPE (
	REIMB_TYPE_ID int primary key unique,
	REIMB_TYPE varchar(10) not null unique 
);

insert into ERS_REIMBURSEMENT_TYPE (REIMB_TYPE_ID,REIMB_TYPE) values (1,'LODGING');

insert into ERS_REIMBURSEMENT_TYPE (REIMB_TYPE_ID,REIMB_TYPE) values (2,'FOOD');

insert into ERS_REIMBURSEMENT_TYPE (REIMB_TYPE_ID,REIMB_TYPE) values (3,'TRAVEL');

-- Creating a table to keep track of roles for the users
create table ERS_USER_ROLES (
	ERS_USER_ROLE_ID int primary key unique,
	USER_ROLE varchar(10) not null unique
);

















select * from ers_reimbursement_type ert ; 