uperApp.controller("applicationController", function($scope, uperService, $location, $routeParams 	 ){

var responseHandler = new ResponseHandler($scope);
var MINOR_REQUIRED_DOCUMENT_COUNT=3;
var ADULT_REQUIRED_DOCUMENT_COUNT=3;


$scope.application={};
$scope.paymentTypes=[{'value':'NONE', 'type':'None'},{'value':'CASH', 'type':'Cash'}, {'value':'CHECK', 'type':'Check'},{'value':'CREDIT_CARD', 'type':'Credit Card'} , {'value':'MONEY_ORDER', 'type': 'Money Order'}];
$scope.disableStatus=true;
$scope.disableForm=false;
$scope.applicationStatus=[{'value':'IN_PROCESS', 'label': 'In Process'},{'value':'APPROVED', 'label': 'Approved'}];

 
$scope.initApplication=function(){
	var stateFileNumber = $routeParams.stateFileNumber;
	var patientId = $routeParams.patientId;
	
	if (typeof stateFileNumber != "undefined"){
		console.log("Initialize Patient's Application Form: " + stateFileNumber);
		uperService.getApplication(stateFileNumber).then(function(response){
			responseHandler.processCommands(response.commands);
		});
	 } 
	
 }; 



$scope.onApplicationLoad=function(application){
	 if ('APPROVED' === application.applicationStatus){
		$scope.disableStatus=true;
		$scope.disableForm=true;
	 }
	 

};

$scope.onApplicationLoadError=function(){
	console.log("An issue was encountered loading patient application.");
};


$scope.calcDates=function(){
	if ('APPROVED' === $scope.application.applicationStatus){
		var today = new Date();
	    var dd = today.getDate();
		var mm=today.getMonth() +1;
	 	var curYear=today.getFullYear();
		var nextYear=today.getFullYear()+1;
		
		var issueDate =   mm+"-"+today.getDate()+"-"+curYear  ;
		var expDate =   mm+"-"+today.getDate()+"-"+nextYear  ;
		
		$scope.application.issuedDate = issueDate;
		$scope.application.expirationDate=expDate; 
		
	}else{
		$scope.application.issuedDate = "";
		$scope.application.expirationDate="";
	}

	$scope.saveApplication();
};



$scope.saveApplication=function(){
	uperService.saveApplication( $scope.application  ).then(function(response){
		responseHandler.processCommands(response.commands);
	});
};

$scope.printApplication = function(){
	 $scope.saveApplication();
	 window.open( 'printApplication/'+$scope.application.stateFileNumber  ) ;
};

 

//SAVE DOCUMENTS
$scope.saveDocument=function( document, isChecked ){
  if (isChecked){
		uperService.saveDocument( $scope.application.patientApplicationId, document ).then(function(response){
			responseHandler.processCommands(response.commands);
		});
	}else{
		uperService.deleteDocument( $scope.application.patientApplicationId, document ).then(function(response){
			responseHandler.processCommands(response.commands);
		});
	}
};
//CALL BACK FOR DOCUMENT CHANGE
$scope.onDocumentChange=function(){
	console.log("Application Document changes were successful.");
	$scope.enableStatusControl();
};
 

//SAVE PAID
$scope.savePaymentType=function(){
   uperService.savePaymentType( $scope.application.patientApplicationId, $scope.application.paymentType ).then(function(response){
	 	responseHandler.processCommands(response.commands);
 	});
 };

$scope.savePayment=function(){
   	uperService.savePayment($scope.application.patientApplicationId, $scope.application.paymentReceived).then(function(response){
 		responseHandler.processCommands(response.commands);
	 });
};

//CALL BACK FOR DOCUMENT CHANGE
$scope.onDocumentChange=function(){
	console.log("Application Document changes were successful.");
	$scope.enableStatusControl();
};

//CALL BACK FOR PAYMENT CHANGE
$scope.onPaymentChange=function(){
	console.log("Payment Changes changes were successful.");
	$scope.enableStatusControl();
};


$scope.backToPatient=function(){
 	$location.url('patientDetail/'+ $scope.application.patientId);
};


//JAVASCRIPT (JQUERY) to loop through Documents to enable/disable Status Select Box
$scope.enableStatusControl = function(){
	var selected = [];
	var requiredDocumentsReceived=false;
	
		if( $scope.application.minor){
			 $j('.minor input:checked').each(function() {
		    	selected.push( $j(this).attr('name'));
			});
			if (MINOR_REQUIRED_DOCUMENT_COUNT === selected.length){
			  	requiredDocumentsReceived = true;
			} 
		}else{
			 $j('.adult input:checked').each(function() {
		    	selected.push( $j(this).attr('name'));
			});
			if (ADULT_REQUIRED_DOCUMENT_COUNT === selected.length){
				requiredDocumentsReceived = true;
			} 
		}	
		//if all documents and payment received enable the status select box.
		if(requiredDocumentsReceived && $scope.application.paymentReceived  ){
			$scope.disableStatus = false;
		} else{
	  		$scope.disableStatus = true;
		}
		

};



});