app.controller('welcomeCtrl', ["$scope", function($scope) {
	
	$scope.role = localStorage.getItem('authLevel');
	
	
}]);
