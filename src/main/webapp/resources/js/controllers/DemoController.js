uperApp.controller("demoController", function($scope, demoService ){
	
	 
$scope.registeredUsers=[];
 

$scope.registerUser=function(user){
	$scope.newUser={};
	$scope.newUser.firstName=user.firstName;
	$scope.newUser.lastName=user.lastName;
	$scope.newUser.middleName=user.mastName;
	$scope.registeredUsers.push($scope.newUser);
 };

 

$scope.getRegisteredUsers=function(){
	 var promise =  demoService.getUsers( );
	 
	 promise.then(function( data ){  
		 console.log("SUCCESS");
		 $scope.registeredUsers=data; //<------ SUCESS
	 },function(reason){
		 console.log("FAILURE");
		 alert("Error Handler:  " + reason);  //<------ FAILURE
	 });
};

$scope.getNoUsers=function(){
	 var promise =  demoService.getNoUsers( );
	 promise.then(function( data ){  
		 console.log("SUCCESS");
		 $scope.registeredUsers=data; //<------ when the promise is returned, then put data into a javascript array that is bound to your html document.
	 },function(reason){
		 console.log("FAILURE");
		 alert("Error Handler:  " + reason);  //<----- HANDLE ERROR
	 });
};


$scope.isDirty=function(isDirty){
	 alert("Is form Dirty? " + isDirty);
};

$scope.isValid=function(isValid){
	 alert("Is Form Valid?  " + isValid);
};





//CODE TO CONTROL UP/DOWN
var move = function (origin, destination) {
    var temp = $scope.registeredUsers[destination];
    $scope.registeredUsers[destination] = $scope.registeredUsers[origin];
    $scope.registeredUsers[origin] = temp;
};

$scope.moveUp = function(index){
    move(index, index - 1);
};

$scope.moveDown = function(index){                    
    move(index, index + 1);
};

 







});