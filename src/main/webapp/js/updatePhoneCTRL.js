app.controller('updatePhoneCtrl', ['$scope', '$http', 'formValidationSvc', function($scope, $http, formValidationSvc) {
  $scope.incorrectPassword = "";
  $scope.phoneBadFormat = "";
  $scope.successMessage = "";

  $scope.formChangePhoneNumber = function(){
		// Checks if the requested new phone number matches validations
		// PUT VALIDATEPASSWORD HERE WHEN WE KNOW IT WORKS
    var result = formValidationSvc.validatePhoneFormat($scope.newPhoneNumber);
		if(result === false){// CHECK PHONE NUMBER AGAINST VALIDATIONS
			$scope.phoneResult = "Phone number must be in format ##########";
		} else {
			var phoneStr = "userEmail=" + $scope.email +
							"&authTok=" + $scope.user.authTkn/*TODO replace this when use cookies*/ +
							"&infoType=" + "phone"/*TODO change according to info being changed*/ +
							"&newInfo=" + $scope.newPhoneNumber;
			$http({
		        method: 'POST',
		        url: 'RULServlet/chengeUserInfo',
		        data: phoneStr,
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(){ //TODO use data from response from service ChangeUserInfoService
						$scope.phoneResult = "Phone number successfully changed!";
			});
		}

	};
}]);
