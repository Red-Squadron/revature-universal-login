/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */
module
.controller('loginCtrl', function($scope, $http) {
	$scope.loginSuccess = "";
	$scope.user = "";
	$scope.user.authLvl = "";
	$scope.user.authTkn = "";
	$scope.validateLogin = function(userName, passPhrase) {
		var validateString = "";
		validateString += "userName=" + userName + "&password=" + passPhrase;
		
		// If the email address does not contain '@' then nothing will happen
		//if(userName.includes("@")){ //TODO
			$http({
    	        method: 'POST',
    	        url: 'RULServlet/login',
    	        data: validateString,
    	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(userInfo) {
    			if(userInfo.valid === "true") {
    				$scope.user.authLvl = userInfo.authLvl;
    				$scope.user.authTkn = userInfo.authTkn;
    				$scope.loginSuccess = "Successfully logged in!";
    				
    				
    		//stores the information in html5  session storage, if the tab is closed then the  storage is deleted
    				
    				setHtmlStorage(autholevel,userInfo.authLvl)
    				localStorage.loggedIn = $scope.user.authLvl;
    				localStorage.loggedIn = $scope.user.authtkn;
    				
    				
    				//TODO cookie implementation instead of in scope
			    } else
			    	$scope.loginSuccess = "Wrong email/password!";
    			
		    })
		//} else {
			// EMAIL DOES NOT CONTAIN AN @
		//}
	};
});