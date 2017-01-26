--insert user info into regstration
create or replace procedure add_new_user
(uEmail in varchar2, firstname in varchar2, middleName in varchar2,
lastName in varchar2, phoneNumber in number, passwd in varchar2, outval out varchar2)
is
begin
if not exists (select * from userRegistration where userEmail = uEmail) then
insert into userRegistration values (uEmail, firstName, middleName, lastName,
phoneNumber, passwd);
else
outval := false;
end if;
end;
/


--checks for duplicate previous passwords
create or replace procedure passwd_checker
(uEmail in varchar2, passwrd in varchar, outval out varchar2)
is
a varchar2;
begin
if not exists (select * from passwordHistory where pastPass = passwrd) then
insert into userRegistration (passwd) values (passwrd);
else
outval := false;
end if;
end;
/

