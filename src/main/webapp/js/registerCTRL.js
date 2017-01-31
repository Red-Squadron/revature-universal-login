/**
 * Reads user input, AJAX Posts as json
 * {userName: String, passphrase: String, firstName: String, middleName:<String,undefined;>}
 */
module
.controller('registerCtrl', function($scope, $http) {
	$scope.registerUser = function(userName, passPhrase, firstName, lastName, middleName) {
		newUser = {};
		newUser.userName = userName;
		newUser.passphrase = passPhrase;
		newUser.firstName = firstName;
		newUser.lastName = lastName;
		newUser.lastName = middleName;
		
		$http.post("RegistrationServer/login", newUser).success(function(isRegistered) {
			if(isRegistered === "true") {
				Angular.element("modalClose").triggerHandler("click");
			}
		})
		
		$scope.hovering = false;
		
		$scope.changeColor = function(bgColor, textColor){
			$scope.rb = {
					background : bgColor,
					color : textColor
			}
		}
	};
});