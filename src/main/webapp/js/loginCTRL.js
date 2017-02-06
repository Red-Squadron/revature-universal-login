/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */
app
.controller('loginCtrl', ["$scope", "$http", "$location", function($scope, $http, $location) {
    $scope.loginSuccess = "";
    $scope.user = {};
    $scope.user.authLvl = "";
    $scope.user.authTkn = "";

    $scope.validateLogin = function(userName, passPhrase, view) {
        var validateString = "";
        validateString += "userName=" + userName + "&password=" + passPhrase;

        var regemail = /([a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+)@([a-zA-Z]+)\.([a-z]{2,})$/;
		    if(regemail.test(userName)){
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
                    $scope.changeView(view);
                    //var b =document.getElementById("click").click();

                    setHtmlStorage('authoLevel',userInfo.authLvl)
    				        setHtmlStorage('authToken',userInfo.authoTkn)

                    //TODO cookie implementation instead of in scope
                } else
                    $scope.loginSuccess = "Wrong email/password!";

            })
        } else {
        	$scope.loginSuccess = "Invalid email!";
        }
    };

    $scope.changeView = function(view) {
      $location.path(view);
    };
}]);
