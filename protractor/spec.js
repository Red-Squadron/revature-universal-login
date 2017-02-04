// spec.js
var testData = require('./data.json');
	
describe('Login Test', function() {
	
	
	testData.forEach( function (data) {
		it('Should have a functioning login system', function() {
			var username = element(by.id(data.userId));
			var password = element(by.id(data.passId));
			var goButton = element(by.id(data.buttonId));
			
			browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
				
			username.sendKeys(data.userData);
			password.sendKeys(data.passData);
			
			goButton.click();
			
			expect(element(by.binding('loginSuccess')).getText()).toEqual(data.responseText);
		  });
    });
});