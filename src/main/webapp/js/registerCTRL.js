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
			var data = "userEmail="+userName+"&firstName="+firstName+"&lastName="+lastName+"&middleName="+middleName+"&phoneNumber="+phoneNumber+"&password="+passPhrase;
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/register',
    	        data: data,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(isNotRegistered) {
				if(isNotRegistered === "true") { 
					$scope.pass = "Success ! You are registered !";
				}
				else
					$scope.pass = "Unable to register this account. Either you are already registered, or this is not a valid email.";
			}).error(function() {
				$scope.pass = "Something has gone wrong with the server. Please try again later."
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
