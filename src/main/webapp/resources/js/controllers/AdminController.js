uperApp.controller("adminController", function($scope, uperService, $location ){
	
var responseHandler = new ResponseHandler($scope);	 
 
$scope.lastName="";
$scope.firstName="";
$scope.stateFileNumber="";

$scope.showUsers=function(){
 	$location.url("userList/");
}
$scope.generateReport=function(){
	 window.open( 'applicationReport'  );
};
$scope.generateXML=function(){
   window.open( 'rest/patient/' + $scope.lastName +"/" + $scope.firstName);
};

$scope.generateXMLStateFileNumber=function(){
 	window.open( 'rest/application/' + $scope.stateFileNumber);
 };

$scope.returnPatient=function(){
	$location.url("patientDetail/");
};


$scope.clearForm=function(search){
	if ('nameSearch'=== search){
		$scope.lastName="";
		$scope.firstName="";
	}else{
		$scope.stateFileNumber="";
	}		
};

//JAVA SCRIPT TO SUPPORT USER SCREENS
//USER LIST
$scope.user={};
$scope.users=[];
$scope.initUserList=function(){
	console.log("Initialize User List.");
	uperService.getUsers().then(function(response) {
		responseHandler.processCommands(response.commands);
	});
};

$scope.showUserDetail=function(user){
	console.log("Show User Detail.");
	$scope.user=user;
};

$scope.saveUser=function(){
	uperService.saveUser($scope.user).then(function(response) {
		responseHandler.processCommands(response.commands);
	});
};

$scope.toggleActive=function(user){
	if (user.active){
		 user.active=false;
	}else{
		 user.active=true;
	}
 	uperService.saveUser(  user ).then(function(response) {
		responseHandler.processCommands(response.commands);
	});
};

});