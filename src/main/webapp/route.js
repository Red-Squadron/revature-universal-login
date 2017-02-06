var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/", {
            templateUrl : "login.html",


        })
        .when("/register", {
            templateUrl : "register.html",


        })
          .when("/profiles", {
            templateUrl : "changeInfo.html",
          })
        .when("/welcome",
        {
        	 templateUrl : "welcome.html",
        })
        .when("/updatepassword",
        {
        	 templateUrl : "updatepassword.html",
        })
        .when("/updatephone",
        {
        	 templateUrl : "updatephone.html",
        })

        .otherwise({
        	templateUrl : "404.html",
        });

});
