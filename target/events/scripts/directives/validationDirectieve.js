app.directive('passvalidation', function() {
  return {
    restrict: 'A',
    require: 'ngModel',
    link: function(scope, element, attributes, controller) {
      controller.$validators.pass = function(value) {
        //more than 5 chars and without spaces
        if (typeof value === "undefined") {
          return false;
        }
        else{
          return (value.length >= 5 && !(/\s/.test(value)))
        }
      }
    }
  }
})

app.directive('emailvalidation', function() {
  return {
    restrict: 'A',
    require: 'ngModel',
    link: function(scope, element, attributes, controller) {
     controller.$validators.email=function(value){
         if (typeof value === "undefined") {
          return false;
        }
        else{
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(value);
        }
     }
    }
  }
})
