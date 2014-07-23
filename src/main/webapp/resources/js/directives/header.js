'use strict';

uperApp.directive('header',function(){
	return{
		restrict: 'A',
		replace: true, 
		templateUrl: "resources/js/partials/header.html"
	
	}

});