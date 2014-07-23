'use strict';
uperApp.controller("mainController", function($scope, $timeout ){

console.log("Main Controller");

var responseHandler = new ResponseHandler($scope);
$scope.messages = [];
$scope.messageStyle="alert-success";

$scope.addMessage = function(message) {
	 if (typeof message.style != 'undefined'){
	   $scope.messageStyle = message.style;
	 }
		
	$scope.messages.push(message);
	
	if(message.displaySeconds) {
		var displayLength = message.displaySeconds ? message.displaySeconds * 1000 : 5000; 
		$timeout(function() {
			$scope.removeMessage(message);
		}, displayLength);			
	}
};

$scope.removeMessage = function(message) {
	$scope.messages = _.without($scope.messages, message);
};

});