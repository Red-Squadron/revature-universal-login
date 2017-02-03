/**
 * Controller for change information page.
 * Allows user to change their password and/or phone number.
 * User must supply their email and current password to change information.
 */

//error here
app.controller('changeInformationCtrl', function($scope, $http){
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
		var validatePassword = false;
		//var validateStr = $scope.userEmailModel + ":" + $scope.currentPasswordModel;
		var validateStr = "authTkn="+ $scope.user.authTkn;/*TODO replace this when use cookies*/ 
		$http({
	        method: 'POST',
	        url: 'RULServlet/authenticate',
	        data: validateStr,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(){
			validatePassword = true;
		});
		
		// Checks if the requested password matches validations
		if(false){ // PUT VALIDATEPASSWORD HERE WHEN WE KNOW IT WORKS
			$scope.incorrectPassword = "Incorrect password!";
		} else if($scope.firstPasswordModel !== $scope.secondPasswordModel){
			$scope.notEqual = "Passwords not equal!";
		} else if($scope.firstPasswordModel.length < 8){
			$scope.badFormat = "Password must be at least eight characters!";
		} else if($scope.firstPasswordModel.length > 75){
			$scope.badFormat = "Password must be at most seventy-five characters!";
		} else if($scope.firstPasswordModel === $scope.firstPasswordModel.toLowerCase()){
			$scope.badFormat = "Password must contain at least one uppercase letter!";
		} else if($scope.firstPasswordModel === $scope.firstPasswordModel.toUpperCase()){
			$scope.badFormat = "Password must contain at least one lowercase letter!";
		} else if(!containsNumber($scope.firstPasswordModel)){
			$scope.badFormat = "Password must contain at least one number!";
		} else if(!containsSpecial($scope.firstPasswordModel)){
			$scope.badFormat = "Password must contain at least one special character!";
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
		
		// Checks if the current password is correct for the user's email
		var validatePassword = false;
		var validateStr = "userEmail=" + $scope.userEmailModel + "&password=" + $scope.currentPasswordModel;
		$http({
	        method: 'POST',
	        url: 'RULServlet/login',
	        data: validateStr,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(){
			validatePassword = true;
		});
		
		// Checks if the requested new phone number matches validations
		if(false){// PUT VALIDATEPASSWORD HERE WHEN WE KNOW IT WORKS
			$scope.incorrectPassword = "Incorrect password!";
		} else if(false){// CHECK PHONE NUMBER AGAINST VALIDATIONS
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
	        }).success(function(data){ //TODO use data in response from service ChangeUserInfoService
				$scope.successMessage = "Phone number successfully changed!";
			});
		}
	};
});

// Checks if the passed string contains a number
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