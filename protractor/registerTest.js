
describe('Register Test', function() {
	var registerBtn = element(by.id('registerBtn'));
	var fname = element(by.name('firstName'));
	var mname = element(by.name('middleName'));
	var lname = element(by.name('lastName'));
	var phone = element(by.name('phoneNumber'));
	var username = element(by.name('username'));
	var pass = element(by.name('password'));
	var passConfirm = element(by.name('passwordConfirm'));
	var submitButton = element(by.className('input-sm'));
	var success = element(by.binding('passs'));

  beforeEach(function() {
    browser.get('http://localhost:7001/Revature_Universal_Login/login.html');
    registerBtn.click();
  });

  it('should have a title', function() {
	    expect(browser.getTitle()).toEqual('Login');
	  });
  
  it('Should fail with no info', function() {
	  submitBtn.click();
	  expect(success.getText()).toEqual('Checking');
  });


});
