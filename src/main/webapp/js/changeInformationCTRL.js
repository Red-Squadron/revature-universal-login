// Checks if phone number follows ########## format
var validatePhoneFormat = function(str) {
	var rephone = /[0-9]{10}/;
	return rephone.text(str);
}

// Checks if the passed string contains a number
var containsNumber = function(str){
	var renum = /[0-9]+/;
	return renum.test(str);
};

// Checks if the passed string contains a special character
var containsSpecial = function(str){
	var respecial = /[-=\[\]\\\;\,\.\/~!@#\$%\^&\*()\_\+{}\|:<>\?]+/;
	return respecial.test(str);
};

var validatePasswordFormat = function(firstPassword, secondPassword) {
	var result = {pass: true, err: ""};
	if(firstPassword !== secondPassword){
		result.pass = false;
		result.err = "Passwords not equal";
	} else if(firstPassword.length < 8){
		result.pass = false;
		result.err = "Password must be at least eight characters!";
	} else if(firstPassword.length > 75){
		result.pass = false;
		result.err = "Password must be at most seventy-five characters!";
	} else if(firstPassword === firstPassword.toLowerCase()){
		result.pass = false;
		result.err = "Password must contain at least one uppercase letter!";
	} else if(firstPassword === firstPassword.toUpperCase()){
		result.pass = false;
		result.err = "Password must contain at least one lowercase letter!";
	} else if(!containsNumber(firstPassword)){
		result.pass = false;
		result.err = "Password must contain at least one number!";
	} else if(!containsSpecial(firstPassword)){
		result.pass = false;
		result.err = "Password must contain at least one special character!"
	}
	return result;
}

/**
 * Controller for change information page.
 * Allows user to change their password and/or phone number.
 * User must supply their email and current password to change information.
 */

//error here
app.controller('changeInformationCtrl', ["$scope", "$http", function($scope, $http) {
	$scope.incorrectPassword = "";
	$scope.badFormat = "";
	$scope.notEqual = "";
	$scope.phoneBadFormat = "";
	$scope.successMessage = "";

	/**
	 * Function used to update a user's password
	 *
	 * $scope.userEmailModel: The user's email address
	 * $scope.currentPasswordModel: The user's current password
	 * $scope.firstPasswordModel: The user's requested new password
	 * $scope.secondPasswordModel: Re-entered password for confirmation
	 *
	 * $scope.incorrectPassword: Shows an error message if the user's current password is incorrect
	 * $scope.badFormat: Shows an error message if the new password does not match validations
	 * $scope.notEqual: Shows an error message if the first and second new passwords do not match
	 * $scope.successMessage: Shows a success message if the $http.post succeeds
	 */

	$scope.formChangePassword = function(){
		$scope.incorrectPassword = "";
		$scope.badFormat = "";
		$scope.notEqual = "";
		$scope.successMessage = "";

		// Checks if the current password is correct for the user's email
		var validateStr = "authTkn="+ $scope.user.authTkn;/*TODO replace this when use cookies*/
		$http({
					method: 'POST',
					url: 'RULServlet/authenticate',
					data: validateStr,
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}).success(function(){
		});
		var validationResult = validatePasswordFormat($scope.firstPasswordModel, $scope.secondPasswordModel);
		if(validationResult.pass === false){
			$scope.badFormat = validationResult.err;
			console.log(validationResult.err);
		} else {
			var passwordStr = "userEmail="+$scope.userEmailModel + "&password=" + $scope.firstPasswordModel;
			 $http.post("RULServlet/changeUserPassword", passwordStr).success(function(){
			  	$scope.successMessage = "Password successfully changed!";
			 });
		}
	};

	/**
	 * Function used to update a user's password
	 *
	 * $scope.userEmailModel: The user's email address
	 * $scope.currentPasswordModel: The user's current password
	 * $scope.phoneChangeModel: The user's requested new phone number
	 *
	 * $scope.incorrectPassword: Shows an error message if the user's current password is incorrect
	 * $scope.phoneBadFormat: Shows an error message if the phone number does not match validations
	 * $scope.successMessage: Shows a success message if the $http.post succeeds
	 */
	$scope.formChangePhoneNumber = function(){
		$scope.incorrectPassword = "";
		$scope.phoneBadFormat = "";
		$scope.successMessage = "";

		// Checks if the requested new phone number matches validations
		// PUT VALIDATEPASSWORD HERE WHEN WE KNOW IT WORKS
		if(validatePhoneFormat($scope.phoneChangeModel)){// CHECK PHONE NUMBER AGAINST VALIDATIONS
			$scope.phoneBadFormat = "Invalid phone number! Use ########## format."
		} else {
			var phoneStr = "userEmail=" + $scope.userEmailModel +
							"&authTok=" + $scope.user.authTkn/*TODO replace this when use cookies*/ +
							"&infoType=" + "phone"/*TODO change according to info being changed*/ +
							"&newInfo=" + $scope.phoneChangeModel;
			$http({
		        method: 'POST',
		        url: 'RULServlet/chengeUserInfo',
		        data: phoneStr,
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(){ //TODO use data from response from service ChangeUserInfoService
						$scope.successMessage = "Phone number successfully changed!";
			});
		}

	};
}]);
