var DemoService = function($http, $q, $timeout) {
	var service = {};

	service.getUsers=function( ){
		 var deferred =  $q.defer();
		 
		 //JSON data returned from an ajax call
		 var returnedData = [{'firstName':'Ralph','lastName':'No More','middleName':'S'},{'firstName':'Anna','lastName':'Carole','middleName':'S'},{'firstName':'John','lastName':'Anderson','middleName':'T'},{'firstName':'Emma','lastName':'Blakley','middleName':'Tina'}];
		 
		 $timeout(function(){
			if (returnedData.length > 3){
				deferred.resolve(returnedData);   //<-----RESOLVE
			}else{
				deferred.reject("NO DATA RETURNED");  
			}	 
		  }, 10);
		   
		 
		   return deferred.promise;  //<--------PROMISE IS RETURNED.
   };
   
   

service.getNoUsers=function( ){
	 var deferred =  $q.defer();
	 var returnedData  = []; //<------Successful ajax call, but it is an error because there is no data.
	 
	 $timeout(function(){
		 if (returnedData.length > 3){
				deferred.resolve(data);
		 }else{
				deferred.reject("NO DATA RETURNED...");  //<--- REJECT-> No Data reject (similiar to throwing an error)
		 }	 
		  }, 10);
	   
	     
		return deferred.promise;  
		
 
};




   



  



	return service;
};

uperApp.factory("demoService", DemoService);
