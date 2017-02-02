--procedure to pull a user and check its existence
--alevel is permissions returned, needs to be handled client side to be transferred to 
--add_new_user procedure
create or replace procedure check_user_existence
(uEmail in varchar2, fname in varchar2, lname in varchar2, alevel out varchar2, a out number)
is
uem SalesForceMock.userEmail%type;
fnam SalesForceMock.firstName%type;
lnam SalesForceMock.lastName%type;
cursor c is
select userEmail, firstName, lastName from SalesForceMock;
begin
a := 0;
open c;
loop
fetch c into uem, fnam, lnam;
if ((uEmail like uem) and (fname like fnam) and (lname like lnam)) then
a := 1;
select permissions into alevel from SalesForceMock where userEmail = uEmail
and firstName = fname and lastName = lname;
--dbms_output.put_line(alevel);
end if;
exit when c%notfound;
end loop;
close c;
end;
/



----test find user procedure
--declare
--uEmail varchar2(75); fName varchar2(30); lName varchar2(30); b varchar2(25);
--a number;
--begin
--check_user_existence('newuser@email.com', 'thisguy', 'isawesome', :b, :a);
--end;
--/