/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */
module
.controller('loginCtrl', function($scope, $http) {
	$scope.validateLogin = function(userName, passPhrase) {
		var validateString = "";
		validateString += "userName=" + userName + "&password=" + passPhrase;
		
		// If the email address does not contain '@' then nothing will happen
		if(validateString.userName.includes("@")){
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/login',
    	        data: validateString,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(userInfo) {
    			if(userInfo.valid === "true") {
    				$scope.user.authLvl = userInfo.authLvl;
    				$scope.user.authTkn = userInfo.authTkn;
    				//TODO cookie implementation instead of in scope
			    }
		    })
		} else {
			// EMAIL DOES NOT CONTAIN AN @
		}
	};
});