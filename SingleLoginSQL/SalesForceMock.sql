--SalfesForce Databse
Create table SalesForceMock(
userEmail varchar2(75) primary key not null,
firstName varchar2(30) not null,
lastName varchar2 (30) not null,
permissions varchar(25) not null
);


insert into SalesForceMock values (
'jrivera@email.com', 'justin', 'rivera', 'associate'
);
insert into SalesForceMock values (
'nickpaqu@email.com', 'nick', 'paquette', 'associate'
);
insert into SalesForceMock values (
'matteck@email.com', 'matthew', 'eckert', 'associate'
);
insert into SalesForceMock values (
'seancav@email.com', 'sean', 'cavanaugh', 'associate'
);
insert into SalesForceMock values (
'newuser@email.com', 'thisguy', 'isawesome', 'associate'
);
insert into SalesForceMock values (
'davidgr@email.com', 'david', 'green', 'associate'
);
insert into SalesForceMock values (
'oscar@email.com', 'oscar', 'grouch', 'associate'
);
insert into SalesForceMock values (
'yuvi@email.com', 'yuvi', 'damodaran', 'trainer'
);
insert into SalesForceMock values (
'admin@email.com', 'admin', 'admin', 'admin'
);