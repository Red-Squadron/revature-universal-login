var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/", {
            templateUrl : "login.html",
            controller : "loginCtrl"
        })
        .when("/register", {
            templateUrl : "register.html",
            controller : "registerCtrl"
        })
        .when("/profiles", {
            templateUrl : "changeInfo.html",
            controller : "changeInformationCtrl"
        })
        .when("/welcome", {
        	 templateUrl : "welcome.html",
           controller : "welcomeCtrl"
        })
        .when("/updatepassword", {
        	 templateUrl : "updatepassword.html",
           controller : "updatePasswordCtrl"
        })
        .when("/updatephone", {
        	 templateUrl : "updatephone.html",
           controller : "updatePhoneCtrl"
        })
        .otherwise({
        	templateUrl : "404.html",
        });

});
