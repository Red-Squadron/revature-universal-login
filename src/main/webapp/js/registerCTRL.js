/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
module
.controller('registerCtrl', function($scope, $http) {
	$scope.registerUser = function(userName, firstName, lastName, middleName, passPhrase, passPhraseConfirm) {
		newUser = {};
		newUser.userName = userName;
		newUser.passphrase = passPhrase;
		newUser.firstName = firstName;
		newUser.lastName = lastName;
		newUser.lastName = middleName;
		newUser.passPhraseConfirm = passPhraseConfirm;
		
		if(newUser.passphrase !== newUser.passPhraseConfirm){
			
		} else if(newUser.passphrase.length < 8){
			
		} else if(newUser.passphrase.length > 75){
			
		} else if(newUser.passphrase === newUser.passphrase.toLowerCase()){
			
		} else if(newUser.passphrase === newUser.passphrase.toUpperCase()){
			
		} else if(!containsNumber(newUser.passphrase)){
			
		} else if(!containsSpecial(newUser.passphrase)){
			
		} else {
			var data = "userEmail="+userName+"&firstName="+firstName+"&lastName="+lastName+"&middleName="+middleName+"&phoneNumber="/*TODO phone number*/+"&password="+passPhrase;
			$http.post("RULServlet/register", data).success(function(isRegistered) {
				if(isRegistered === "true") {
					Angular.element("modalClose").triggerHandler("click");
				}
			})
		}
		
		$scope.hovering = false;
		
		$scope.changeColor = function(bgColor, textColor){
			$scope.rb = {
					background : bgColor,
					color : textColor
			}
		}
	};
});

//Checks if the passed string contains a number
var containsNumber = function(str){
	if(str.includes('0')){
		return true;
	} else if(str.includes('1')){
		return true;
	} else if(str.includes('2')){
		return true;
	} else if(str.includes('3')){
		return true;
	} else if(str.includes('4')){
		return true;
	} else if(str.includes('5')){
		return true;
	} else if(str.includes('6')){
		return true;
	} else if(str.includes('7')){
		return true;
	} else if(str.includes('8')){
		return true;
	} else if(str.includes('9')){
		return true;
	} else {
		return false;
	}
};

// Checks if the passed string contains a special character
var containsSpecial = function(str){
	if(str.includes('-')){
		return true;
	} else if(str.includes('=')){
		return true;
	} else if(str.includes('[')){
		return true;
	} else if(str.includes(']')){
		return true;
	} else if(str.includes('\\')){
		return true;
	} else if(str.includes(';')){
		return true;
	} else if(str.includes(',')){
		return true;
	} else if(str.includes('.')){
		return true;
	} else if(str.includes('/')){
		return true;
	} else if(str.includes('~')){
		return true;
	} else if(str.includes('!')){
		return true;
	} else if(str.includes('@')){
		return true;
	} else if(str.includes('#')){
		return true;
	} else if(str.includes('$')){
		return true;
	} else if(str.includes('%')){
		return true;
	} else if(str.includes('^')){
		return true;
	} else if(str.includes('&')){
		return true;
	} else if(str.includes('*')){
		return true;
	} else if(str.includes('(')){
		return true;
	} else if(str.includes(')')){
		return true;
	} else if(str.includes('_')){
		return true;
	} else if(str.includes('+')){
		return true;
	} else if(str.includes('{')){
		return true;
	} else if(str.includes('}')){
		return true;
	} else if(str.includes('|')){
		return true;
	} else if(str.includes(':')){
		return true;
	} else if(str.includes('<')){
		return true;
	} else if(str.includes('>')){
		return true;
	} else if(str.includes('?')){
		return true;
	} else {
		return false;
	}
};