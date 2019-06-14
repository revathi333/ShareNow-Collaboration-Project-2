app.controller("blogCommentController",function($scope,$location,$http,$rootScope)
{	
	$scope.CommentsList;
	
	$scope.Blog={"blogId":0,"blogName":"","blogContent":"","username":"","likes":"","status":"","createDate":"","blogUpdatedDate":"","dislikes":""};
   
	$scope.BlogComment={"blogCommentId":0,"commentText":"","commentedDate":"","username":"","commentLikes":"","blogId":0,"commentUpdatedDate":""};

	$scope.editComment={"blogCommentId":0,"commentText":"","commentedDate":"","username":"","commentLikes":"","blogId":0,"commentUpdatedDate":""};
 
	function LoadBlog()
     {
	     $http.get("http://localhost:8086/ShareNowMiddleware/getBlog/"+$rootScope.blogId)
		 .then(function(response)
		     {
		         $scope.Blog=response.data;
		     });	 		
	  }	 
	 
	
	function LoadBlogComments()
    {
    	$http.get("http://localhost:8086/ShareNowMiddleware/showAllBlogComments/"+$rootScope.blogId)
	 	 .then(function(response)
	         {
	 	    	 $scope.CommentsList=response.data;	 	    	 	 	    	
	         },
	           function(response)
	              {
	        	      $scope.CommentsList = null;
   	              });
    }
	
	 $scope.addBlogComment = function(blogId)
	    {
		    $scope.BlogComment.blogId = blogId;
	    	$http.post("http://localhost:8086/ShareNowMiddleware/addBlogComment",$scope.BlogComment)
	     	.then(function(response)
		       {
	     		  $scope.BlogComment.commentText="";
	     		  LoadBlogComments();    //To display added blogComment in the list without refreshing page
	     		  $location.path("/blogComments"); 		  
		       });		
	    }
	 
	 $scope.deleteBlogComment = function(blogCommentId)
	    {
	    	$http.get("http://localhost:8086/ShareNowMiddleware/deleteBlogComment/"+blogCommentId)
	     	.then(function(response)
		       {
	     		  LoadBlogComments();    //To display blogComments after deleting (without refreshing page)
	     		  $location.path("/blogComments");
		       });		
	    }
	 
	 
	 $scope.editBlogComment = function(blogCommentId)
	    {
		    $http.get("http://localhost:8086/ShareNowMiddleware/getBlogComment/"+blogCommentId)
	     	.then(function(response)
		       {
	     		  $scope.editComment = response.data;
	     		  LoadBlogComments();    //To display added blogComment in the list without refreshing page
	     		  $location.path("/blogComments");
		       });		
	    }
	 
	 
	 $scope.updateBlogComment = function()
	    {	 
		   $http.post("http://localhost:8086/ShareNowMiddleware/updateBlogComment",$scope.editComment)
	        .then(function(response)
		    {
	     		$scope.editComment.blogCommentId=0;
	     		LoadBlogComments();    //To display added blogComment in the list without refreshing page
	     		$location.path("/blogComments");
		    });		
	    }
	 
	 
	LoadBlog();
	 
    LoadBlogComments();
	
});
