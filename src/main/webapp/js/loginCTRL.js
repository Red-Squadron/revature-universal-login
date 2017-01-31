/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */
module
.controller('loginCtrl', function($scope, $http) {
	$scope.validateLogin = function(userName, passPhrase) {
		var validateString = {};
		validateString.userName = userName;
		validateString.password = passPhrase;
		
		
		// If the email address does not contain '@' then nothing will happen
		if(validateString.userName.includes("@")){
			$http.post("RULServlet/login", validateString).success(function(userInfo) {
				if(userInfo.valid === "true") {
					$scope.user.authLvl = userInfo.authLvl;
					$scope.user.userName = userInfo.userName;
				}
			})
		} else {
			// EMAIL DOES NOT CONTAIN AN @
		}
	};
});