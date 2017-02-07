/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
app.controller('registerCtrl', ["$scope", "$http", "formValidationSvc", function($scope, $http, formValidationSvc) {
	$scope.pass = "Checking";
	$scope.registerUser = function(userName, firstName, lastName, middleName, phoneNumber, passPhrase, passPhraseConfirm) {

		var validationResult = formValidationSvc.validatePasswordFormat(passPhrase, passPhraseConfirm);
		if (validationResult.pass === false) {
			$scope.pass = validationResult.err;
		} else {
			$scope.pass = "start Register";
			var data = "userEmail="+userName+"&firstName="+firstName+"&lastName="+lastName+"&middleName="+middleName+"&phoneNumber="+phoneNumber+"&password="+passPhrase;
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/register',
    	        data: data,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(isRegistered) {
				if(isRegistered === "true") { 
					$scope.pass = "Success ! You are registered !";
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
}]);
