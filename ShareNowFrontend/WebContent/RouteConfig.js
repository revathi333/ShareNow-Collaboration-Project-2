var app = angular.module("appModule",["ngRoute","ngCookies"]);
                 
app.config(function($routeProvider)
		        {
	                   $routeProvider
                	   .when("/login",{templateUrl:"c_user/Login.html"})
              	       .when("/register",{templateUrl:"c_user/Register.html"})
            	       .when("/userHome",{templateUrl:"c_user/UserHome.html"})
            	       .when("/invalidUser",{templateUrl:"c_user/UserNotFound.html"})            	                  	                    	       .when("/addBlog",{templateUrl:"c_blog/My_Wall.html"})
              	       .when("/publicBlogs",{templateUrl:"c_blog/PublicBlogs.html"})
                       .when("/adminCheck",{templateUrl:"c_blog/AdminCheck.html"})
                       .when("/blogComments",{templateUrl:"c_blogComment/BlogComments.html"})                       
                       .when("/publishJob",{templateUrl:"c_job/Publish_Job.html"})
                       .when("/jobsDisplay",{templateUrl:"c_job/JobsDisplay.html"})
                       .when("/chat",{templateUrl:"c_chat/Chat.html"})
                       .when("/friends",{templateUrl:"c_friend/Friends.html"});     
                 });

app.run(function($rootScope,$cookieStore)
    {
			
	  if($rootScope.currentUser==undefined)
		 {
		     $rootScope.currentUser = $cookieStore.get("userdetails");
         }
    });





