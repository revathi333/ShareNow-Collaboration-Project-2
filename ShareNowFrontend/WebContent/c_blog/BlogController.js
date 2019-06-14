app.controller("blogController",function($scope,$location,$http,$rootScope)
{
	$scope.blog={"blogId":"","blogName":"","blogContent":"","username":"","likes":"","status":"","createDate":"","blogUpdatedDate":"","dislikes":""};

	$scope.blogData;
	
	$scope.LoggedUserBlogs;
	
	$scope.AdminCheckBlogs;

	$rootScope.blogId;
				
    function showAllBlogs()
    {	 //Only status:'A' blogs will be displayed for public   	
	   $http.get("http://localhost:8086/ShareNowMiddleware/showAllBlogs")
	   .then(function(response)
	      {
		     $scope.blogData = response.data;
	      },
	       function(response)
	         {
		        $scope.blogData = null; //If there is no blog,previous values given to $scope.blogData will be replaced with null/empty value.
	         });			
    }

    function showBlogsforAdmin()
    {	
	   $http.get("http://localhost:8086/ShareNowMiddleware/showBlogsforAdmin")
	   .then(function(response)
	      {
		     $scope.AdminCheckBlogs = response.data;
	      },
	        function(response)
	          {
		         $scope.AdminCheckBlogs = null;
	          });			
    }

    
    function showLoggedUserBlogs()
    {
      if($rootScope.currentUser!=undefined)
        {
    	   var user = $rootScope.currentUser.username;
	       $http.get("http://localhost:8086/ShareNowMiddleware/showBlogsByUser/"+user)
	       .then(function(response)
	          {
	     	      $scope.LoggedUserBlogs = response.data;
	          },
	            function(response)
	              {
	    	         $scope.LoggedUserBlogs=null;
	              });	
        }
    }

    
    $scope.addBlog = function()
    {
     	$http.post("http://localhost:8086/ShareNowMiddleware/addBlog",$scope.blog)
     	.then(function(response)
	       {
     		   $scope.blog.blogName="";
     		   $scope.blog.blogContent="";
     		
     	       showLoggedUserBlogs();    //To display added blog in the list without refreshing page
     	        $location.path("/addBlog");		      
	      });		
    }	
    
    
    $scope.deleteBlog = function(blogId)
    {    
    	$http.get("http://localhost:8086/ShareNowMiddleware/deleteBlog/"+blogId)
     	.then(function(response)
	       {    	
     	      showLoggedUserBlogs();     //To display rest of the blogs without refreshing page      	      
   	           $location.path("/addBlog");
		  
     	   });		
    }
    
    
    $scope.approveBlog = function(blogId)
    {
    	$http.get("http://localhost:8086/ShareNowMiddleware/approveBlog/"+blogId)
     	.then(function(response)
	        {    	
     	       showBlogsforAdmin();     //To display reject option (without refreshing page) after approve button is clicked    	       		       
			    $location.path("/adminCheck");
	
		       
	        });		
    }	
    
    
    $scope.rejectBlog = function(blogId)
    {    	
    	$http.get("http://localhost:8086/ShareNowMiddleware/rejectBlog/"+blogId)
     	.then(function(response)
	        {
     	       showBlogsforAdmin();     //To display approve option (without refreshing page) after reject button is clicked
     	        $location.path("/adminCheck");

	       });		
    }	
    
	
    var UserLikedBlog = {"blogLikes":{"blogId":"","username":""},"likedValue":0,"dislikedValue":0};

	
  //ng-click Like Button
    
    $scope.LikeBlog = function(blogId)
    { 			
    	$http.get("http://localhost:8086/ShareNowMiddleware/getUserLikedBlog/"+blogId)
	    .then(function(response)
          {	        	
	          UserLikedBlog=response.data;
	            	        	              
               if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==0)
                   {			        		
    	              $http.get("http://localhost:8086/ShareNowMiddleware/incLikesBlog/"+blogId)
     	             .then(function(response)
                        {     	        	     	                	
     	   		            $http.get("http://localhost:8086/ShareNowMiddleware/updateLikeValue/"+blogId)
                           .then(function (response)
                           	{
                    	        showLoggedUserBlogs(); 
         	                    $location.path("/addBlog");
                            });
     	             
                      });	    	        
                   }
    	

    	     else if(UserLikedBlog.likedValue==1 && UserLikedBlog.dislikedValue==0)
                  {		
          	   	      $http.get("http://localhost:8086/ShareNowMiddleware/decLikesBlog/"+blogId)
     	              .then(function(response)
                        {
     	          	         $http.get("http://localhost:8086/ShareNowMiddleware/updateLikeValue/"+blogId)
         		            .then(function (response)
         		              {
                   	              showLoggedUserBlogs(); 
        	                      $location.path("/addBlog");
                              });
                        });	   	        
                  }
    	
    	
        else if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==1)
    	         {		
    		         $http.get("http://localhost:8086/ShareNowMiddleware/decDisLikesincLikes/"+blogId)
    		 	    .then(function(response)
    		           {
    		 		       $http.get("http://localhost:8086/ShareNowMiddleware/updateLike_DislikeValue/"+blogId)
        		          .then(function (response)
        		    	     {
        		    	        showLoggedUserBlogs(); 
           	                    $location.path("/addBlog");
                             });
    		          });	
    		     }
          });
    }	
    
  
    //ng-click Dislike Button
    
    $scope.DisLikeBlog = function(blogId)
    {
    	$http.get("http://localhost:8086/ShareNowMiddleware/getUserLikedBlog/"+blogId)
        .then(function(response)
           {
        	  UserLikedBlog=response.data;
     
    	      if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==0)
                 {
    		        $http.get("http://localhost:8086/ShareNowMiddleware/incDisLikesBlog/"+blogId)
     	           .then(function(response)
                      {
     	    	         $http.get("http://localhost:8086/ShareNowMiddleware/updateDislikedValue/"+blogId)
                        .then(function (response)
        		        	{                     	        		    	                      	  
                      	       showLoggedUserBlogs(); 
           	                   $location.path("/addBlog");
                            });                      	            
                     });	
    	        }
     
     
    	     else if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==1)
    	        {
        		    $http.get("http://localhost:8086/ShareNowMiddleware/decDisLikesBlog/"+blogId)
    	 	       .then(function(response)
    	               {
    	 		    	  $http.get("http://localhost:8086/ShareNowMiddleware/updateDislikedValue/"+blogId)
      		             .then(function (response)
       		    	         {                     	       		    	                     	  
                                showLoggedUserBlogs(); 
          	                    $location.path("/addBlog");
                             });
    	               });		    		    
    	       }
     
     
    	  else if(UserLikedBlog.likedValue==1 && UserLikedBlog.dislikedValue==0)
    	    {		
    		   $http.get("http://localhost:8086/ShareNowMiddleware/decLikesincDislikes/"+blogId)
    	 	   .then(function(response)
    	          {
    	 		      $http.get("http://localhost:8086/ShareNowMiddleware/updateLike_DislikeValue/"+blogId)
      		         .then(function (response)
          		    	 {
                             showLoggedUserBlogs(); 
             	             $location.path("/addBlog");
                         });
    	          });	
    		 }
        });
     }	
    
    
    $scope.BlogComment = function(blogId)
    { 
    	$rootScope.blogId = blogId;
        $location.path("/blogComments");	        	 		
    }	 
    
      
 showAllBlogs();
 
 showBlogsforAdmin();

 showLoggedUserBlogs();
 
});









