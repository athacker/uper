'use strict';
 

uperApp.directive('capitalizeFirst', function() {
	   return {
		     require: 'ngModel',
		     link: function(scope, element, attrs, modelCtrl) {
		        var capitalize = function(inputValue) {
		        	var capitalized="";
		        	if (typeof inputValue != 'undefined'){
		        		var noNumbers = inputValue.replace(/(\d+)/g, '');
		        		noNumbers = noNumbers.replace(/[^\w\s]/gi, '');
		        		capitalized = noNumbers.charAt(0).toUpperCase() + inputValue.substring(1); //Format incoming value
		        		
		        		if(capitalized !== inputValue ) {
		                	 modelCtrl.$setViewValue(capitalized);//sets the rootScope value.
		                	 modelCtrl.$render();//called when view needs to be updated
		                }
		        		if (noNumbers !== inputValue){
		        			modelCtrl.$setViewValue(noNumbers);
		        			modelCtrl.$render();//called when view needs to be updated
		        		}
		                
		             }//undefined
		                
		            return capitalized;
		         } 
		         modelCtrl.$parsers.push(capitalize);//$parser pushes an array of function to execute
		         capitalize(scope[attrs.ngModel]);  // capitalize initial value
		     }
		   };
		});

 
 