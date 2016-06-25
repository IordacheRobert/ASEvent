app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/addEvent', {
    templateUrl: 'views/addEvent.html',
    controller: 'eddEventController'
  });
}]);


app.controller('eddEventController',['$scope','$http','userService','$location',function($scope,$http,userService,$location) {
  $scope.profileButton=userService.getAuthenticationState();
  $scope.userInfo=userService.getUser();
  $scope.logout=function(){userService.log_out();}
 
  $scope.eventDataLink="http://www.tutorialspoint.com/"
  
   $scope.callback = function(response){
    console.log(response);
    alert('share callback');
  }
  /////////////////////////
  $scope.today = function() {
    $scope.dt_start = new Date();
    $scope.dt_end = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt_start = null;
    $scope.dt_end = null;
  };


  $scope.open1 = function() {
    $scope.popup1.opened = true;
  };

  $scope.open2 = function() {
    $scope.popup2.opened = true;
  };

  $scope.popup1 = {
    opened: false
  };

  $scope.popup2 = {
    opened: false
  };

    
    
    
  $scope.showDate=function(){
      
      var temp={
          "id": 16,
            "created":$scope.dt_start,
          "content":"temp"
      }
      console.log(temp);
      
    $http.post('/events/webapi/comments/test',temp
      ).then(function successCallback(response) {
            console.log(response.data);
            
          
        }, function errorCallback(response) {
         
        });
  }

}]);
