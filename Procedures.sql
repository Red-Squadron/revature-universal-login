--insert user info into regstration
create or replace procedure add_new_user
(uEmail in varchar2, fName in varchar2, mName in varchar2,
lName in varchar2, pNumber in number, passwrd in varchar2)
is
em userRegistration.userEmail%type;
a number;
cursor c is
select userEmail from userRegistration;
begin
open c;
loop
fetch c into em;
if uEmail like em then
a := 1;
else
a := 0;
end if;
exit when c%notfound;
end loop;
close c;
if a = 0 then
insert into userRegistration values (uEmail, fName, mName, lName,
pNumber, passwrd);
insert into passwordHistory values (uEmail, passwrd);
dbms_output.put_line('success');
else
dbms_output.put_line('fail');
end if;
end;
/

----call procedure to prove it works
--declare
--uEmail varchar2(75); fName varchar2(30); mName varchar2(30);
--lName varchar2(30); pNumber number(10); passwrd varchar2(30);
--begin
--add_new_user('newuser@email.com', 'thisguy', null, 'isawesome', 1234567890, 'password');
--end;
--/

--checks for duplicate previous passwords
create or replace procedure passwd_checker
(uEmail in varchar2, passwrd in varchar)
is
ps passwordHistory.pastPass%type;
a number;
cursor c is
select pastPass from passwordHistory;
begin
open c;
loop
fetch c into ps;
if passwrd like ps then
a := 1;
else
a := 0;
end if;
exit when c%notfound;
end loop;
close c;
if a = 0 then
update userRegistration set passwd = passwrd where userEmail = uEmail;
insert into passwordHistory values (uEmail, passwrd);
dbms_output.put_line('success');
else
dbms_output.put_line('fail');
end if;
end;
/


----tests the update password procedure
--declare
--uEmail varchar2(75); passwrd varchar(50);
--begin
--passwd_checker('newuser@email.com', 'passwd');
--end;
--/