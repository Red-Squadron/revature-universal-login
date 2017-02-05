// spec.js
var testData = require('./users.json');
	
describe('Login Test', function() {
	
	testData.forEach( function (data) {
		var username = element(by.id(data.userId));
		var password = element(by.id(data.passId));
		var goButton = element(by.id(data.buttonId));
		it('Should have a functioning login system', function() {
			
			browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
				
			username.sendKeys(data.userData);
			password.sendKeys(data.passData);
			
			goButton.click();
			
			if(element(by.binding('side-line-inner')).getText().includes("Sign-In")){
				expect(element(by.binding("loginSuccess")).toEqual(data.responseText))
			}else{
				expect(element(by.binding('side-line-inner')).getText().toEqual(data.title))
			}
			
		  });
    });
	
	
});