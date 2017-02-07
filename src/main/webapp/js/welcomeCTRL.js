app.controller('welcomeCtrl', ["$scope", function($scope) {
	
	$scope.role = getHtmlStorage('authLevel');
	
	
}]);
