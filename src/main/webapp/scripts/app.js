var app = angular.module('eventsApp', [
  'ngRoute',
  'ngResource',
  'uiGmapgoogle-maps',
  'google.places',
  'ui.bootstrap',
  'djds4rce.angular-socialshare'
]);

app.config(['$routeProvider', function($routeProvider) {
 
    
  $routeProvider.otherwise({
    redirectTo: '/events'
  });
}]);

app.run(function($FB){
  $FB.init('1019058011496889');
});