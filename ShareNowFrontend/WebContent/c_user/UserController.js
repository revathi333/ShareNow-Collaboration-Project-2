app.controller("userController",function($scope,$http,$location,$rootScope,$cookieStore)
{
	$scope.user = {"username":"","password":"","memberName":"","emailId":"","role":"","status":"","isOnline":""};
	
	$scope.loginCheck = function()
	{		
		$http.post("http://localhost:8086/ShareNowMiddleware/checkUser",$scope.user)
		 .then(function(response)
		   {
			  $scope.user = response.data;
			  $rootScope.currentUser = response.data;
			  $cookieStore.put("userdetails",response.data);
			  $location.path("/userHome");
		   },
		     function()
		       {
				   $location.path("/invalidUser");
		       });
	}
		
	$scope.logout = function()
	{
		delete $rootScope.currentUser;
		$cookieStore.remove("userdetails");
		$location.path("/login");
	}
		
	$scope.register = function()
	{
		$http.post("http://localhost:8086/ShareNowMiddleware/addUser",$scope.user)
		  .then(function(response)
		     {		  
			     $location.path("/login");
		     });
	}		

});


