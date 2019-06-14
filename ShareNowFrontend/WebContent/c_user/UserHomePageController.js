app.controller("userHomeController",function($scope,$location,$http,$rootScope)
{
	$scope.blogs;
	
	function allBlogs()
    {	    	
	   $http.get("http://localhost:8086/ShareNowMiddleware/showAllBlogs")
	   .then(function(response)
	      {
		      $scope.blogs = response.data;
	      },
	       function(response)
	         {
		        $scope.blogs = null;
	         });			
    }
	
    $scope.deleteBlog = function(blogId)
    {
       $http.get("http://localhost:8086/ShareNowMiddleware/deleteBlog/"+blogId)
     	.then(function(response)
	       {    	
     		  allBlogs();     //To display rest of the blogs without refreshing page  
		      $location.path("/userHome");
	       });		
    }
    
	var UserLikedBlog = {"blogLikes":{"blogId":"","username":""},"likedValue":0,"dislikedValue":0};
		
	  //ng-click Like Button
	    
	    $scope.LikePublicBlog = function(blogId)
	    { 		
	    	$http.get("http://localhost:8086/ShareNowMiddleware/getUserLikedBlog/"+blogId)
		        .then(function(response)
	          {		        	
		        	UserLikedBlog = response.data;		            	           
	    
	               if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==0)
	                   {				        		
	    	              $http.get("http://localhost:8086/ShareNowMiddleware/incLikesBlog/"+blogId)
	     	              .then(function(response)
	                          {	     	        	
	     	   		             $http.get("http://localhost:8086/ShareNowMiddleware/updateLikeValue/"+blogId)
	                             .then(function (response)
	                    	         {	                    	  	                    	  
	                    	            allBlogs(); 
	             	                    $location.path("/userHome");
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
	                                       allBlogs(); 
        	                               $location.path("/userHome");
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
	                                 	allBlogs(); 
            	                        $location.path("/userHome");
	                                });
	    		           });		    			
	    		    }
	         });
	    }	
	    
	  
	    //ng-click Dislike Button
	    
	    $scope.DisLikePublicBlog = function(blogId)
	    {
	    	$http.get("http://localhost:8086/ShareNowMiddleware/getUserLikedBlog/"+blogId)
	        .then(function(response)
	            {	        	
	        	    UserLikedBlog = response.data;
	     	    	    	
	    	        if(UserLikedBlog.likedValue==0 && UserLikedBlog.dislikedValue==0)
	                   {	    			    		        		
	    	               $http.get("http://localhost:8086/ShareNowMiddleware/incDisLikesBlog/"+blogId)
	     	               .then(function(response)
	                           {
	       		                   $http.get("http://localhost:8086/ShareNowMiddleware/updateDislikedValue/"+blogId)
	                               .then(function (response)
	        		    	          {	                      		        		    	 	                      	  
	                      	              allBlogs(); 
            	                          $location.path("/userHome");
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
	                     	                allBlogs(); 
             	                            $location.path("/userHome");
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
	                        	         allBlogs(); 
	             	                     $location.path("/userHome");
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
	    	   	    
	    allBlogs();
	    
});

