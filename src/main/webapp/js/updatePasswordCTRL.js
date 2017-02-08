app.controller('updatePasswordCtrl', ['$scope', '$http', 'formValidationSvc', function($scope, $http, formValidationSvc) {
  $scope.incorrectPassword = "";
  $scope.badFormat = "";
  $scope.notEqual = "";
  $scope.successMessage = "";

  $scope.formChangePassword = function() {
    // Checks if the current password is correct for the user's email
    var validationResult = formValidationSvc.validatePasswordFormat($scope.newPassPhrase, $scope.passPhraseConfirm);
    if(validationResult.pass === false){
      $scope.badFormat = validationResult.err;
    } else {
      var passwordStr = "userEmail="+$scope.email + "&password=" + $scope.newPassPhrase;
      //  $http.post("RULServlet/changeUserPassword", passwordStr).success(function(){
      //     $scope.successMessage = "Password successfully changed!";
      //  });
      $http({
		        method: 'POST',
		        url: 'RULServlet/changeUserPassword',
		        data: passwordStr,
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	        }).success(function(){ //TODO use data from response from service ChangeUserInfoService
						$scope.successMessage = "Password successfully changed!";
			});
    }
  };
}]);
