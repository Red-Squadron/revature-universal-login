/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
module
.controller('loginCtrl', function($scope, $http) {
	$scope.registerUser = function(userName, passPhrase, firstName, lastName, middleName) {
		newUser = {};
		newUser.userName = userName;
		newUser.passphrase = passPhrase;
		newUser.firstName = firstName;
		newUser.lastName = lastName;
		newUser.lastName = middleName;
		
		$http.post("RegistrationServer", newUser.toJSON).success(function(isRegistered) {
			if(isRegistered === "true") {
//				celebrate
			}
		})
	};
});