app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/events', {
    templateUrl: 'views/events.html',
    controller: 'eventsController'
  });
}]);

app.controller('eventsController',['$scope','$http','userService',function($scope,$http,userService) {

  $scope.profileButton=userService.getAuthenticationState();
  $scope.userInfo=userService.getUser();
  $scope.logout=function(){userService.log_out();}
  console.log('User state='+userService.getAuthenticationState());

//  $http({
//    method: 'GET',
//    url: '/events/webapi/events'
//  }).then(function successCallback(response) {
//      $scope.events=response.data;
//      console.log(response.data);
//    }, function errorCallback(response) {
//      $scope.users="no events"
//    });





}]);
