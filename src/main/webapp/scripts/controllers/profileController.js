app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/profile', {
    templateUrl: 'views/profile.html',
    controller: 'profileController'
    // ,
    // resolve:{
    //     "check":function($location,userService){
    //         if(userService.getAuthenticationState()){
    //         }else{
    //            $location.path('/events');//redirect to events
    //         }
    //     }
    // }
  });
}]);



app.controller('profileController',['$scope','$http','userService','$location','locationService',function($scope,$http,userService,$location,locationService) {
  
    
    $scope.profileButton=userService.getAuthenticationState();

    $scope.userInfo=userService.getUser();
    
    $scope.logout=function(){userService.log_out();} 
    
    $scope.myScopeVar=null;
    
    $scope.map = { center: { latitude: $scope.userInfo.value.location.latitude, longitude:      $scope.userInfo.value.location.longitude }, zoom:16};
    
    
//    userService.get_my_events().then(function(events){
//         $scope.userPublishedEvents=events;
//    })
//  
    
    //true mean editable and false read-only
    $scope.infoState=false;
    
    
    $scope.editUser=function(){
         $scope.infoState=true;
    }
    
   $scope.saveUserInfo=function(){
       $scope.infoState=false;
       
       $scope.userInfo.value.location.latitude=$scope.myScopeVar.geometry.location.lat();
       $scope.userInfo.value.location.longitude=$scope.myScopeVar.geometry.location.lng();
       
       $scope.map = { center: { latitude: $scope.userInfo.value.location.latitude, longitude:      $scope.userInfo.value.location.longitude }, zoom:16};
       
       $scope.userInfo.value.location.city=$scope.myScopeVar.formatted_address.split(',')[1];
       $scope.userInfo.value.location.country=$scope.myScopeVar.formatted_address.split(',')[2];
       $scope.userInfo.value.location.address=$scope.myScopeVar.formatted_address.split(',')[0];
       
       console.log($scope.myScopeVar);
       //change location and update user in DB
       locationService.saveLocation($scope.userInfo.value.location).then(function(newLocation){
           $scope.userInfo.value.location=newLocation;
           userService.update($scope.userInfo);
       })
       
       
       
   }
  
}]); 
