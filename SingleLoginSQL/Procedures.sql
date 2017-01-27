--insert user info into regstration
create or replace procedure add_new_user
(uEmail in varchar2, fName in varchar2, mName in varchar2,
lName in varchar2, pNumber in number, passwrd in varchar2, a out number)
is
ema userRegistration.userEmail%type;
cursor c is
select userEmail from userRegistration;
begin
a := 0;
open c;
loop
fetch c into ema;
if uEmail like ema then
a := 0;
else
a := 1;
end if;
exit when c%notfound;
end loop;
close c;
if a = 1 then
insert into userRegistration values (uEmail, fName, mName, lName,
pNumber, passwrd);
insert into passwordHistory values (uEmail, passwrd, current_timestamp);
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
--a number;
--begin
--add_new_user('newuser@email.com', 'thisguy', null, 'isawesome', 1234567890, 'password', :a);
--end;
--/

--checks for duplicate previous passwords
--if more than 3 pwds exist, will delete the earliest
create or replace procedure passwd_checker
(uEmail in varchar2, passwrd in varchar2, a out number)
is
ps passwordHistory.pastPass%type;
phistcount number;
delstamp passwordHistory.add_stamp%type;
cursor c is
select pastPass from passwordHistory;
begin
a := 0;
open c;
loop
fetch c into ps;
if passwrd not like ps then
a := 1;
end if;
exit when c%notfound;
end loop;
close c;
if a = 1 then
update userRegistration set passwd = passwrd where userEmail = uEmail;
insert into passwordHistory values (uEmail, passwrd, current_timestamp);
dbms_output.put_line('success');
else
dbms_output.put_line('fail');
end if;
select count(*) into phistcount from passwordHistory where userEmail = uEmail;
if phistcount > 3 then
select min(add_stamp) into delstamp from passwordHistory;
delete from passwordHistory where userEmail = uEmail and add_stamp = delstamp;
end if;
end;
/


----tests the update password procedure
--declare
--uEmail varchar2(75); passwrd varchar(50); a number;
--begin
--passwd_checker('newuser@email.com', 'passwrd00d', :a);
--end;
--/


--update phone number
create or replace procedure update_phone
(uEmail in varchar2, phone in number, a out number)
is
em userRegistration.userEmail%type;
--a number;
cursor c is
select userEmail from userRegistration;
begin
a := 0;
open c;
loop
fetch c into em;
if uEmail like em then
a := 1;
update userRegistration set phoneNumber = phone where userEmail = uEmail;
end if;
exit when c%notfound;
end loop;
close c;
end;
/

----call update phone function
--declare
--uEmail varchar2(75); phone number(10); a number;
--begin
--update_phone('newuser@email.com', 2314506849, :a);
--end;
--/


--validate_login
create or replace procedure validate_login
(uEmail in varchar2, passwrd in varchar2, a out number)
is
em userRegistration.userEmail%type;
ps passwordHistory.pastPass%type;
cursor c is
select userEmail, passwd from userRegistration;
begin
a := 0;
open c;
loop
fetch c into em, ps;
if em like uEmail and ps like passwrd then
dbms_output.put_line('success');
a := 1;
end if;
exit when c%notfound;
end loop;
close c;
end;
/


----test check password and username procedure
--declare
--uEmail varchar(75); passwrd varchar(75); a number;
--begin
--validate_login('newuser@email.com', 'passwrd00d',:a);
--end;
--/