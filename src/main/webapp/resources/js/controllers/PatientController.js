uperApp.controller("patientController", function($scope, uperService, $modal, $location, $routeParams   ){
var responseHandler = new ResponseHandler($scope);
 
//PatientBean.java 
$scope.patient={};
$scope.parentAddressSame=false;
 
//initialize patient used in patientDetail.html
$scope.initPatient=function(){
 	var patientId = $routeParams.patientId;
 	
 	if ( typeof patientId  != "undefined"){
 	 	uperService.getPatient( patientId).then(function(response) {
			responseHandler.processCommands(response.commands);
		});
 	 }
};
 

//SAVE PATIENT
$scope.savePatient=function(){
 	uperService.savePatient( $scope.patient ).then(function(response){
		responseHandler.processCommands(response.commands);
	});
};

//SAVE PATIENT call back functions
$scope.onPatientSaved=function(patient){
	$scope.patientForm.$setPristine();
	console.log("Patient was successfully saved.");
};

$scope.onPatientSaveError=function(patient){
	console.log("An error was encountered while saving patient data.");
};

$scope.clearForm=function(isDirty){
	var cancelAndContinue = true;
	 
	if (isDirty){
		cancelAndContinue = confirm("Warning...you have un-saved changes. Confirm OK if you wish to continue and lose edits, or Cancel and then submit your edits."); 
	}
 
	if (cancelAndContinue){
	 	$scope.patient={};
		$location.url('/patientDetail/' );
	}	
};

$scope.resetForm=function(isDirty){
	var cancelAndContinue = true;
	 
	if (isDirty){
		cancelAndContinue = confirm("Warning...you have un-saved changes. Confirm OK if you wish to continue and lose edits, or Cancel and then submit your edits."); 
	}
 
	if (cancelAndContinue){
	    uperService.getPatient($scope.patient.patientId).then(function(response) {
			responseHandler.processCommands(response.commands);
		});
	}	
};



//DOB LOGIC
$scope.$watch('patient.dob', function(){
 	 if ( typeof $scope.patient.dob !== 'undefined'){
 	 	if ($scope.patient.dob.indexOf('-') === -1 ){
	  		$scope.patient.minor=$scope.getAge( $scope.patient.dob ) < 18;
	  	}	
	 }
});

$scope.getAge=function(dateString) {
 	var d = new Date();
    
  	var usermonth = dateString.substring(0,2);
    var userday = dateString.substring(2,4);
    var useryear = dateString.substring(4);
    
    var curday = d.getDate();
    var curmonth = d.getMonth()+1;
    var curyear = d.getFullYear();

    var age = curyear - useryear;
    
	if((curmonth < usermonth) || ( (curmonth == usermonth) && curday < userday   )){
	    age--;
	}

    return age;
}




//copy patient address to parent address
$scope.toggleParentAddress=function(){
	if (!$scope.parentAddressSame){
		$scope.parentAddressSame=true;
	}else{
		$scope.parentAddressSame=false;
	}	
	
	if($scope.parentAddressSame){
		$scope.patient.parentAddressCurrent = $scope.patient.addressCurrent;
		$scope.patient.parentCityCurrent = $scope.patient.cityCurrent;
		$scope.patient.parentStateCurrent = $scope.patient.stateCurrent;
		$scope.patient.parentZipCurrent = $scope.patient.zipCurrent;
	 
	}else{
		$scope.patient.parentAddressCurrent="";
		$scope.patient.parentCityCurrent = "";
		$scope.patient.parentStateCurrent ="";
		$scope.patient.parentZipCurrent ="";
	}
};

//copy city, state based on web-services call for zip code.
$scope.getCityState=function(isParent){
	if (!isParent && 5===$scope.patient.zipCurrent.length){
		uperService.getCityState(isParent, $scope.patient.zipCurrent ).then(function(response){
			responseHandler.processCommands(response.commands);
		});
		
	}else if(isParent && 5===$scope.patient.parentZipCurrent.length){
		uperService.getCityState(isParent, $scope.patient.parentZipCurrent ).then(function(response){
			responseHandler.processCommands(response.commands);
		});
	}
	console.log("Patient State Look Up: " + $scope.patient.zipCurrent );
};


//call back from webservices call to populate fields.
$scope.setCityState=function(address){
 
	if (address.parent){
		$scope.patient.parentCityCurrent =address.city;
		$scope.patient.parentStateCurrent=address.state;
		if (!address.validZip){
			$scope.badZipParent=address.zipCode + " is not a valid zip code.";
			$scope.patient.parentZipCurrent="";
		}else{
			$scope.badZipParent="";
		}	
	}else {
		$scope.patient.cityCurrent=address.city;
		$scope.patient.stateCurrent=address.state;
		if (!address.validZip){
			$scope.badzip=address.zipCode + " is not a valid zip code.";
			$scope.patient.zipCurrent="";
		}else{
			$scope.badzip="";
		}	
	}	
};




	
//after user changes the last name we need to look for duplicates
$scope.nameCheck = function(){
    //only launch the modal when we are entering new patients
	if ( typeof $scope.patient.patientId === 'undefined'){
		console.log("Search database for similar Names");
		uperService.nameCheck( $scope.patient.lastName,$scope.patient.firstName ).then(function(response) {
			responseHandler.processCommands(response.commands);
		});
	}	
};

$scope.onNameCheck = function( duplicateData ){
	if ( typeof duplicateData !== 'undefined'){
		if(duplicateData.length>0){
			console.log("Launch Modal: " + JSON.stringify(duplicateData ));
			
			var modalInstance = $modal.open({
			    templateUrl: 'resources/js/partials/nameCheckModal.html',
			    controller: ModalInstanceCtrl,
			    windowClass: 'app-modal-window',
			    resolve: {
			      items: function () {
			        return duplicateData ;
			      }
			    }
			  });		 

			//the modal returns a promise when closed
			  modalInstance.result.then(function (selectedItem) {
				    $scope.patient = selectedItem;
				    $scope.patientForm.$setPristine();
				    uperService.getPatient(selectedItem.id).then(function(response) {
		  				responseHandler.processCommands(response.commands);
		  			});
				  	console.log("close modal")
				  }, function () { 
					 console.log("dismiss modal")
				});
		} //duplicates.length>0
	}//duplicates not undefined

};


//CheckName Controller
var ModalInstanceCtrl = function ($scope, $modalInstance, items) {
	//put duplicateNames on modal scope
	$scope.items = items;
 
	$scope.ok = function ( item ) {
	  	$modalInstance.close(item);
	};
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};	
};


 
$scope.createApplication = function(){
	var cancelAndContinue = true;
	 
	if (null !== $scope.patient.stateFileNumbers &&  0 !==$scope.patient.stateFileNumbers.length ){
		cancelAndContinue = confirm("Warning.. Patient has previously submitted an application.  Confirm OK to submit another application OR Cancel to access application history window.");
	}
	if (cancelAndContinue ){
		uperService.createApplication($scope.patient.patientId  ).then(function(response){
			responseHandler.processCommands(response.commands);
		});
	}	
};

 
$scope.onApplicationCreated = function(application){
	console.log("Application has been created, need to re-set path variables.");
 	var url= '/getApplication/'+  application.stateFileNumber   ;  
	$location.path(url);
};



$scope.getApplication = function( isDirty, stateFileNumber){
	var cancelAndContinue = true;
	 
	if (isDirty){
		cancelAndContinue = confirm("Warning...you have un-saved changes. Confirm OK if you wish to continue and lose edits, or Cancel and then submit your edits."); 
	}
 
	if (cancelAndContinue){
		$location.url('/getApplication/' + stateFileNumber );
	}	 
};

$scope.doplLookup=function(){
 	window.open( 'https://secure.utah.gov/llv/search/index.html' );
};

$scope.boardCertSiteLookup=function(){
   window.open( 'https://application.abpn.com/verifycert/verifycert.asp' );
};











});