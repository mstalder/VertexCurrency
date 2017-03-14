(function(){
	
	"use strict";

	angular.module('scoolapp',[]).controller('controller1',function($scope,$http) {
		
			$http.get("contact.json")
		    .then(function(response) {
		    	$scope.contacts  = response.data;
		    });

		});
})();