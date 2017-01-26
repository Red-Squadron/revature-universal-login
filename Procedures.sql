create or replace procedure add_new_user
(userEmail in varchar2, firstname in varchar2, middleName in varchar2,
lastName in varchar2, phoneNumber in number, passwd in varchar2)
is
begin
insert into userRegistration values (userEmail, firstName, middleName, lastName,
phoneNumber, passwd);
end;
/