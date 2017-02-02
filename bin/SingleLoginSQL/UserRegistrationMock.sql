--2nd database
create table userRegistration(
userEmail varchar(75) primary key not null,
firstName varchar(30),
middleName varchar(30),
lastName varchar(30) not null,
phoneNumber varchar(20),
passwd varchar(75) not null,
permissions varchar2(25) not null
);

create table passwordHistory(
userEmail varchar(75) not null,
pastPass varchar(75) not null,
add_stamp timestamp with time zone,
constraint fk_userEmailPassHist foreign key (userEmail)
references userRegistration(userEmail)
);