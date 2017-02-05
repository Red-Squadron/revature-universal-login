/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */

/**
 * Reads user input, AJAX Posts as string userName:password
 * Expects AJAX response as json {userName: String, authLvl: String, valid: String}
 * Establishes user session
 */
app
.controller('loginCtrl', function($scope, $http) {
    $scope.loginSuccess = "";
    $scope.user = {};
    $scope.user.authLvl = "";
    $scope.user.authTkn = "";

    $scope.validateLogin = function(userName, passPhrase) {
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

                    var b =document.getElementById("click").click();

                    //TODO cookie implementation instead of in scope
                } else
                    $scope.loginSuccess = "Wrong email/password!";

            })
        } else {
        	$scope.loginSuccess = "Invalid email!";
        }
    };
});
