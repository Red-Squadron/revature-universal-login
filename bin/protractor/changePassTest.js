// changePassTest

var testData = require('./changeUsers.json');
	
describe('Change Pass Test', function() {
	
	testData.forEach( function (data) {
		var username = element(by.id(data.userId));
		var password = element(by.id(data.passId));
		
		var oldPass = element(by.id(data.oldPassId));
		var newPass = element(by.id(data.newPassId));
		var newPassConfirm = element(by.id(data.newPassConfirmId));
		
		var loginBtn = element(by.id(""));
		var changePassBtn = element(by.id(""));
		var confirmPass = element(by.id(""));
		
		
		it('Should have a functioning password change system', function() {
			
			browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
				
			username.sendKeys(data.userData);
			password.sendKeys(data.passData);
			
			loginBtn.click();
			
			changePassBtn.click();
			
			oldPass.sendKeys(data.passData);
			newPass.sendKeys(data.newPassData);
			newPassConfirm.sendKeys(data.newPassConfirmData);
			
			
			var titleText = element(by.className('side-line-inner')).getText();
			
			if(titleText === "Update Password"){
				expect (element(by.binding("loginSuccess")).getText()).toEqual(data.responseText);
			}else{
				expect (titleText).toEqual(data.title);
			}
			
		  });
    });
	
	
});