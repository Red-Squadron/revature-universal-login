// spec.js
describe('Login Test', function() {
	var testData = require('./path/to/json.json');
    testData.forEach( function (data) {
		var username = element(by.id(data.userId));
		var password = element(by.id(data.passId));
		var goButton = element(by.id(data.buttonId));
		var success = element(by.id(''));
		
		  beforeEach(function() {
		    browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
		  });
		
		  it('should have a title', function() {
			expect(browser.getTitle()).toEqual('Login');
		  });
		
		  it('Should have a functioning login system', function() {
			username.sendKeys(data.userData);
			password.sendKeys(data.passData);
			
			goButton.click();
			
			expect(success.getText()).toEqual(data.responseText);
		  });
		
    }
});
