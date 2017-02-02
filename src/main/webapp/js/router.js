
	angular.module('login', ['ngRoute'])
	.config(['$routeProvider',function($routeProvider){
		$routeProvider.when('/', {
			template : '<h5> this is the default </h5>'
		})
		.when('/', {
		templateUrl: 'index.html',
		//template : '<h5> this is the second route </h5>'
		})
		.when('/Register',
		{
			templateUrl: 'register.html',

		})
		.when('/ProfileSettings',
		{
		templateUrl: 'changeInfo.html',
		})
		
		.otherwise({redirectTo:'/'});
		
	}]);
	
