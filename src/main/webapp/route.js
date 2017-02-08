var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/", {
            templateUrl : "login.html",
            controller : "loginCtrl",
            requireLogin: false
        })
        .when("/register", {
            templateUrl : "register.html",
            controller : "registerCtrl",
            requireLogin: false
        })
        .when("/profiles", {
            templateUrl : "changeInfo.html",
            controller : "changeInformationCtrl",
            requireLogin: true
        })
        .when("/welcome", {
        	 templateUrl : "welcome.html",
           controller : "welcomeCtrl",
           requireLogin: true
        })
        .when("/updatepassword", {
        	 templateUrl : "updatepassword.html",
           controller : "updatePasswordCtrl",
           requireLogin: true
        })
        .when("/updatephone", {
        	 templateUrl : "updatephone.html",
           controller : "updatePhoneCtrl",
           requireLogin: true
        })
        .otherwise({
        	templateUrl : "404.html",
            requireLogin: false
        });

});

app.run(function($rootScope, $location) {
  $rootScope.$on("$routeChangeStart", function(event, next) {
    // If user is not logged in
    if (localStorage.getItem('authoLevel') == null) {
      // Check if user can access page without logging in
      if (next.requireLogin === false) {
      } else {
        // User does not have access, so redirect to login
        $location.path("/");
      }
    }
  });
});
