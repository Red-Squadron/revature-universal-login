// spec.js
describe('Login Test', function() {
	var testData = require('./path/to/json.json');
    testData.forEach( function (data) {
		var username = element(by.id(data.userId));
		var password = element(by.id(data.passId));
		var goButton = element(by.xpath('//*[@id="loginForm"]/input[3]'));
		var success = element(by.id(''));
		
		  beforeEach(function() {
		    browser.get('http://localhost:7001/Revature_Universal_Login/login.html');
		  });
		
		  it('should have a title', function() {
			expect(browser.getTitle()).toEqual('Login');
		  });
		
		  it('Should not log in with bad info', function() {
			username.sendKeys('MyName');
			password.sendKeys('MyPass');
			
			goButton.click();
			
			expect(success.getText()).toEqual('Wrong email/password!');
		  });
		
		  it('Should login with correct info', function() {
			username.sendKeys('matteck@email.com');
			password.sendKeys('mylongpass');
			
			goButton.click();
			expect(success.getText()).toEqual('Successfully logged in!');
		  });
    }
});
