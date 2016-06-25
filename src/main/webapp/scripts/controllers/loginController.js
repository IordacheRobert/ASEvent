app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'views/login.html',
    controller: 'loginController'
  });
}]);


app.controller('loginController',['$scope','$http','userService','$location',function($scope,$http,userService,$location) {
  $scope.profileButton=userService.getAuthenticationState();
  
   $scope.login= function(){
        console.log(userService.getUser());//test pruposes
        userService.login($scope.form.email,$scope.form.password).then(function () {
        $scope.alertState=userService.getLoginState();
        $scope.profileButton=userService.getAuthenticationState();
        console.log(userService.getUser());//test pruposes
       })
     }


}]);
