app.service('userService',['$http','$q','$location',function($http,$q,$location){
    var user={};
    user.authenticationState=false;
    user.loginState=-1;
    user.published_events={};
    
    
    this.login=function(email,password){

        var defer = $q.defer()

        $http({
        method: 'POST',
        url: '/events/webapi/users/login',
        headers:{
            "email":email,
            "password":password
        }
      }).then(function successCallback(response) {
          user.value=response.data;
          user.authenticationState=true;
          user.loginState=1;
          $location.path('/profile');
          defer.resolve();
        }, function errorCallback(response) {
          user.loginState=0;
          defer.resolve();
        });

         return defer.promise
    }
    
    this.get_my_events=function(){

        var defer = $q.defer()

        $http({
        method: 'GET',
        url: '/events/webapi/users/'+user.value.id+'/events'
      }).then(function successCallback(response) {
          user.published_events=response.data;
          defer.resolve(response.data);
        }, function errorCallback(response) {
          defer.resolve();
        });

         return defer.promise
    }
    
    this.log_out=function(){
        user.authenticationState=false;
        user.published_events={};
        user.value={};
        user.loginState=-1;
        $location.path('/login');
    }
    
    this.check_email=function(email){
        var defer = $q.defer()

        $http({
        method: 'GET',
        url: '/events/webapi/users/email/'+email
      }).then(function successCallback(response) {
          console.log("S-a gasit un user cu email-ul respectiv");
          console.log(response);
          defer.resolve(true);
        }, function errorCallback(response) {
          defer.resolve(false);
        });

         return defer.promise
    }
    
    this.signup=function(formData){
        console.log(formData);
        var userTemp={
            "firstName": formData.first_name,
            "lastName": formData.last_name,
            "email": formData.email,
            "password": formData.password
        }
        
        $http.post('/events/webapi/users', userTemp)
      		.success(function(data, status, headers, config) {
      		            console.log("S-a inregistrat");
                        console.log(data)
      			  		$location.path('/login');
      				})
      		.error(function(data, status, headers, config) {
      					console.log("exceptie sigun"+data);
      				});
    
    
    }
    
    this.update=function(user){
        
        $http.put('/events/webapi/users/'+user.value.id,user.value )
      		.success(function(data, status, headers, config) {
      		            console.log("User update");
                        console.log(data)
      			  	
      				})
      		.error(function(data, status, headers, config) {
      					console.log("exceptie update"+data);
      				});
    }
    
    this.getUser=function(){
        return user;
    }

    this.getAuthenticationState=function () {
      return user.authenticationState;
    }

    this.getLoginState=function(){
      return user.loginState;
    }
    
    this.getPublischedEvents=function(){
      return user.published_events;
    }
    

}]);
