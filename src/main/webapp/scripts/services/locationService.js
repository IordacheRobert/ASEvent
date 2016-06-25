app.service('locationService',['$http','$q',function($http,$q){
    var location={};
    
    
    this.saveLocation=function(location){
         var defer = $q.defer()
         
         console.log(location);
         var temp={
          "latitude": location.latitude,
          "longitude": location.longitude,
          "city": location.city,
          "country": location.country,
          "address": location.address
        }
         

        $http.post('/events/webapi/locations',temp
      ).then(function successCallback(response) {
          location=response.data;
          defer.resolve(response.data);
        }, function errorCallback(response) {
          defer.resolve();
        });

         return defer.promise
        
    }
    
    
    this.getLocation=function(){
        return location;
    }
    

}]);
