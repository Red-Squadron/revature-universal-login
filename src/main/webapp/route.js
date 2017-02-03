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
        .otherwise({
            redirectTo : "/login"
        });
   
});