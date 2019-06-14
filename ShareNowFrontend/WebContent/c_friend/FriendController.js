app.controller("friendController",function($scope,$location,$http,$rootScope){
	
	$rootScope.loggedUsername = $rootScope.currentUser.username;
	
	$scope.showAllFriends;
	
	$scope.sentPendingFriendRequests;
	
	$scope.showReceivedPendingRequests;
	
	$scope.showSuggestedFriends;
	
	$scope.friend;
	
	function showFriends()
      {	    	
	    $http.get("http://localhost:8086/ShareNowMiddleware/showFriends/"+$rootScope.loggedUsername)
	    .then(function(response)
	       {
		      $scope.showAllFriends = response.data;
	       },
             function(response)
              {
	             $scope.showAllFriends = null; //If there is no friend,previous values given to $scope.showFriends will be replaced with null/empty value.
              });			
    }
	
	function sentPendingRequests()
       {	    	
	      $http.get("http://localhost:8086/ShareNowMiddleware/sentPendingFriendRequests/"+$rootScope.loggedUsername)
	      .then(function(response)
	         {
		         $scope.sentPendingFriendRequests = response.data;
	         },
	          function(response)
                {
                   $scope.sentPendingFriendRequests = null; 
                });			
      }	
	
	function showReceivedRequests()
       {	    	
	      $http.get("http://localhost:8086/ShareNowMiddleware/showReceivedPendingRequests/"+$rootScope.loggedUsername)
	      .then(function(response)
	         {
		        $scope.showReceivedPendingRequests = response.data;
	         },
	           function(response)
                 {
                    $scope.showReceivedPendingRequests = null; 
                 });			
      }
	
	function suggestedFriends()
    {	    	
	   $http.get("http://localhost:8086/ShareNowMiddleware/showSuggestedFriends")
	   .then(function(response)
	       {
		       $scope.showSuggestedFriends = response.data;
	       },
	        function(response)
               {
                  $scope.showSuggestedFriends = null; 
               });			
    }

	
	$scope.sendFriendRequest = function(name)
    {	    	
		$scope.friend = {"username":$rootScope.loggedUsername,"friendusername":name,"friendId":0,"status":""};
		
	    $http.post("http://localhost:8086/ShareNowMiddleware/sendFriendRequest",$scope.friend)
	     .then(function(response)
	         {
		        showFriends();
		        sentPendingRequests();
		        showReceivedRequests();
		        suggestedFriends();
		        $location.path("/friends");
	        });			
    }

	$scope.approveFriendRequest = function(friendId)
    {	    	
	   $http.get("http://localhost:8086/ShareNowMiddleware/approveFriendRequest/"+friendId)
	   .then(function(response)
	      {
		      showFriends();
		      sentPendingRequests();
			  showReceivedRequests();
			  suggestedFriends();	
		      $location.path("/friends");
		  });			
    }
	
	$scope.rejectFriendRequest = function(friendId)
    {	    	
	   $http.get("http://localhost:8086/ShareNowMiddleware/rejectFriendRequest/"+friendId)
	   .then(function(response)
		 {
		     showFriends();
		     sentPendingRequests();
			 showReceivedRequests();
			 suggestedFriends();	
		     $location.path("/friends");
		  });		
    }
	
	$scope.Unfriend = function(friendId)
    {	    	
	   $http.get("http://localhost:8086/ShareNowMiddleware/deleteFriend/"+friendId)
	   .then(function(response)
		 {
		     showFriends();
		     sentPendingRequests();
			 showReceivedRequests();
			 suggestedFriends();	
		     $location.path("/friends");
		  });		
    }	
	
	
	showFriends();
	
	sentPendingRequests();
	
	showReceivedRequests();
	
	suggestedFriends();
	
});