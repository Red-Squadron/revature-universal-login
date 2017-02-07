app.controller('navCTRL', ["$scope", "$http", "$rootScope", "$location", function($scope, $http, $rootScope, $location) {

    //Update nav link
    $scope.$watch(function() {
       if($location.url() === "/register") {
           $scope.linkName = "Login";
           $scope.linkURL = $location.absUrl().substring(0,$location.absUrl().indexOf('register'));
       } else if($location.url() === "/welcome") {
           $scope.linkName = "Logout";
           $scope.linkURL = $location.absUrl().substring(0,$location.absUrl().indexOf('welcome'));
       } else if($location.url() === "/updatepassword") {
           $scope.linkName = "Profile" ;
           $scope.linkURL = $location.absUrl().substring(0,$location.absUrl().indexOf('updatepassword')) + "welcome";
       } else if($location.url() === "/updatephone") {
           $scope.linkName = "Profile";
           $scope.linkURL =  $location.absUrl().substring(0,$location.absUrl().indexOf('updatephone')) + "welcome";
       } else if($location.url() === "/") {
           $scope.linkName = "Sign-Up";
           $scope.linkURL = $location.absUrl() + "register";
           // Remove user info
           localStorage.clear();
       } else {
           // Assuming this will be the 404 page
           if(localStorage.getItem('authoLevel') == null) {
               $scope.linkName = "Login";
               $scope.linkURL = $location.absUrl().substring(0,$location.absUrl().indexOf('index.html#'))+"index.html#";
           } else {
               $scope.linkName = "Profile";
               $scope.linkURL = $location.absUrl().substring(0,$location.absUrl().indexOf('index.html#'))+"index.html#/welcome";
           }
       }
   })
}])
