/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
module
.controller('registerCtrl', function($scope, $http) {
	$scope.passwordmessage = "start";
	$scope.registerUser = function(userName, firstName, lastName, middleName, passPhrase, passPhraseConfirm) {
		
		if(passPhrase !== passPhraseConfirm){
			$scope.passwordmessage = "Passwords do not match!";
		} else if(passPhrase.length < 8){
			$scope.passwordmessage = "Password needs at least 8 charachters!";
			
		} else if(passPhrase.length > 75){
			$scope.passwordmessage = "Password can not exceed 75 charachters!";
			
		/*} else if(passPhrase === passPhrase.toLowerCase()){
			$scope.passwordmessage = "Password needs a lowercase letter!";
			
		} else if(passPhrase === passPhrase.toUpperCase()){
			$scope.passwordmessage = "Password needs a uppercase letter!";
			
		} else if(!containsNumber(passPhrase)){
			$scope.passwordmessage = "Password must contain a number!";
			
		} else if(!containsSpecial(passPhrase)){
			$scope.passwordmessage = "Password must contain a special charachter!";
			*/ //TODO Password validation currently does not work all the time
		} else {
			$scope.passwordmessage = "success";
			var data = "userEmail="+userName+"&firstName="+firstName+"&lastName="+lastName+"&middleName="+middleName+"&phoneNumber="/*TODO phone number*/+"&password="+passPhrase;
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/register',
    	        data: data,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).ssuccess(function(isRegistered) {
				if(isRegistered === "true") { //TODO client side visual verification needed
					Angular.element("modal").triggerHandler("click");
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