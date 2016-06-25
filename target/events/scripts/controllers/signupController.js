app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/signup', {
        templateUrl: 'views/signup.html',
        controller: 'signUpController'
    });
}]);


app.controller('signUpController', ['$scope', '$http', 'userService', '$location', function ($scope, $http, userService, $location) {
    $scope.emaiAlert=false;
    $scope.passwordAlert=false;
    $scope.profileButton = userService.getAuthenticationState();
    $scope.userInfo=userService.getUser();
    $scope.signup=function(){
        
        userService.check_email($scope.form.email).then(function(response){
            $scope.emaiAlert=response;
            
            
            if($scope.form.password!=$scope.form.password_confirmation)
                $scope.passwordAlert=true;
            else {
                $scope.passwordAlert=false;
            }


            if($scope.emaiAlert==false && $scope.passwordAlert==false){
                userService.signup($scope.form);
            }
        })
        
        

    
    
    }
    

    
}]);