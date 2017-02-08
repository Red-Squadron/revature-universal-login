app.controller('updatePasswordCtrl', ['$scope', '$http', 'formValidationSvc', function($scope, $http, formValidationSvc) {
  $scope.incorrectPassword = "";
  $scope.badFormat = "";
  $scope.notEqual = "";
  $scope.successMessage = "";

  $scope.formChangePassword = function() {
    // Checks if the current password is correct for the user's email
    console.log("Chaning Password");
    var validationResult = formValidationSvc.validatePasswordFormat($scope.newPassPhrase, $scope.passPhraseConfirm);
    if(validationResult.pass === false){
      $scope.badFormat = validationResult.err;
    } else {
      var passwordStr = "userEmail="+$scope.email + "&password=" + $scope.oldPassPhrase;
       $http.post("RULServlet/changeUserPassword", passwordStr).success(function(){
          $scope.successMessage = "Password successfully changed!";
       });
    }
  };
}]);
