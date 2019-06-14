app.controller("jobController",function($scope,$location,$http,$rootScope)
{
	$scope.Job = {"jobId":"","designation":"","jobDesc":"","lastDatetoApply":"","ctc":"","jobLocation":"","companyName":"","requiredSkills":""};

	$scope.JobsList;
		
	function showJobs()
	{
	   $http.get("http://localhost:8086/ShareNowMiddleware/showAllJobs")
	   .then(function(response)
		  {
		     $scope.JobsList=response.data;
		  },
		     function(response)
		     {
			    $scope.JobsList=null;
		     });	
	}
	
	
	$scope.addJob = function()
	{
	    $http.post("http://localhost:8086/ShareNowMiddleware/addJob",$scope.Job)
	    .then(function(response)
		  {	
	    	  $scope.Job = {"jobId":"","designation":"","jobDesc":"","lastDatetoApply":"","ctc":"","jobLocation":"","companyName":"","requiredSkills":""};
			  alert("Job Posted");
			  showJobs();
			  $location.path("/publishJob");
		  });	
		
	}
	
	$scope.deleteJob = function(jobId)
	{
	    $http.get("http://localhost:8086/ShareNowMiddleware/deleteJob/"+jobId)
	    .then(function(response)
		  {	
			  showJobs();
			  $location.path("/jobsDisplay");
		  });	
		
	}
	
	showJobs();
});


