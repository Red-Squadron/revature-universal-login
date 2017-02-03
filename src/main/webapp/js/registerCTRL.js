/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
app.controller('registerCtrl', function($scope, $http) {
	$scope.pass = "Checking";
	$scope.registerUser = function(userName, firstName, lastName, middleName, phoneNumber, passPhrase, passPhraseConfirm) {

		if(passPhrase !== passPhraseConfirm){
			$scope.pass = "Passwords do not match!";

		} else if(passPhrase.length < 8){
			$scope.pass = "Password needs at least 8 chracters!";

		} else if(passPhrase.length > 75){
			$scope.pass = "Password can not exceed 75 charachters!";

		} else if(passPhrase === passPhrase.toLowerCase()){
			$scope.pass = "Password needs a uppercase letter!";

		} else if(passPhrase === passPhrase.toUpperCase()){
			$scope.pass = "Password needs a lowercase letter!";

		} else if(!containsNumber(passPhrase)){
			$scope.pass = "Password must contain a number!";

		} else if(!containsSpecial(passPhrase)){
			$scope.pass = "Password must contain a special charachter!";

		} else {
			$scope.pass = "start Register";
			var data = "userEmail="+userName+"&firstName="+firstName+"&lastName="+lastName+"&middleName="+middleName+"&phoneNumber="+phoneNumber+"&password="+passPhrase;
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/register',
    	        data: data,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(isRegistered) {
				if(isRegistered === "true") { //TODO client side visual verification needed
					//Angular.element("modal").triggerHandler("click");
					$scope.pass = "success, you registered";
				}
				else
					$scope.pass = firstName + " " +lastName+" with email: "+userName+" does not exist, please contact Revature";
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
	var renum = /[0-9]+/;
	if (renum.test(str)) {
		return true;
	} else {
		return false;
	}
};

// Checks if the passed string contains a special character
var containsSpecial = function(str){
	var respecial = /[-=\[\]\\\;\,\.\/~!@#\$%\^&\*()\_\+{}\|:<>\?]+/;
	return respecial.test(str);
};
