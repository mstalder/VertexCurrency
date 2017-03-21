(function(){
	
	"use strict";

	angular.module('scoolapp',[]).controller('controller1',function($scope,$http) {
		
			$http.get("contact.json")
		    .then(function(response) {
		    	$scope.contacts  = response.data;
		    });
			
			
			$scope.addme = function(name,nachname,alter){
				$scope.contacts.push({"name":name,"nachname":nachname,"alter":alter});
			};
						
			$scope.removeRow = function(name){
				
				$scope.contacts = $.grep($scope.contacts, function(e){ 
				     return e.name != name; 
				});
				
			};

		});
})();