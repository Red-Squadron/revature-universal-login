
var testData = require('./regUsers.json');

describe('Register Test', function() {
	var cornerBtn = element(by.id('cornerBtn'));
	var submitButton = element(by.id('register-button'));
	var success = element(by.binding('pass'));
	
	it('Should have the correct corner button', function(){
		browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
		cornerBtn.click();
		expect (cornerBtn.getText()).toEqual('Log In');
	});
	
	
	testData.forEach( function (data) {
		browser.get('http://ec2-54-161-12-137.compute-1.amazonaws.com:8081/Revature_Universal_Login/index.html');
		cornerBtn.click();
		var fname = element(by.name(data.fName));
		var mname = element(by.name(data.lName));
		var lname = element(by.name(data.mName));
		var phone = element(by.name(data.phoneNumber));
		var username = element(by.id(data.userName));
		var pass = element(by.name(data.pass));
		var passConfirm = element(by.name(data.passConfirm));
		
		it('Should have a functioning registration system', function() {
			
			fname.sendKeys(data.fNameData);
			mname.sendKeys(data.lNameData);
			lname.sendKeys(data.mNameData);
			phone.sendKeys(data.phoneData);
			username.sendKeys(data.userNameData);
			pass.sendKeys(data.passData);
			passConfirm.sendKeys(data.passConfirmData);
			
			submitButton.click();
			
			if(element(by.className('side-line-inner')).getText()==="Registration"){
				expect (success.getText()).toEqual(data.success);
			}else{
				expect (element(by.className('side-line-inner')).getText()).toEqual(data.title);
			}
		});
		
	});

});
