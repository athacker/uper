'use strict';

var UperService = function($http, $log, $q) {
	var service = {};

	service.getCityState=function(isParent, zip){
	  return new RequestHandler().getHttpPromise('address/' + isParent +"/"+ zip + '.json', $http, $q, $log);
	};
	service.savePatient=function(patient){
		  return new RequestHandler().postHttpPromise('patient.json', patient, $http, $q, $log);
	};
	service.saveDocument=function(applicationId, documentId ){
		  return new RequestHandler().getHttpPromise('document/' + applicationId +"/"+ documentId + '.json', $http, $q, $log);
	};
	service.deleteDocument=function(applicationId, documentId ){
		  return new RequestHandler().getHttpPromise('document/delete/' + applicationId+"/"+ documentId + '.json', $http, $q, $log);
	};
	
	service.createApplication=function(patientId){	
		return new RequestHandler().postHttpPromise('application.json', patientId, $http, $q, $log);
	};
	service.getApplication=function(stateFileNumber){	
		return new RequestHandler().getHttpPromise('application/'+ stateFileNumber +'.json', $http, $q, $log);
	};
	service.saveApplication=function(applicationBean){	
		return new RequestHandler().postHttpPromise('saveapplication.json', applicationBean, $http, $q, $log);
	};
	service.nameCheck=function(lastName, firstName){
		  return new RequestHandler().getHttpPromise('patient/'+ lastName +'/'+firstName+'.json', $http, $q, $log);
	};
	service.getPatient=function(patientId){
	   return new RequestHandler().getHttpPromise('patient/'+ patientId + '.json', $http, $q, $log);
	};
	service.getUsers=function(patientId){
	   return new RequestHandler().getHttpPromise('user.json', $http, $q, $log);
	};
	service.saveUser=function(user){
	   return new RequestHandler().postHttpPromise('user.json',user, $http, $q, $log);
	};
	
	service.savePaymentType=function( applicationId, paymentType){
		return new RequestHandler().getHttpPromise('paymentType/'+applicationId+'/'+ paymentType +'.json',  $http, $q, $log);
	};
	
	service.savePayment=function(applicationId, isPaid){
		return new RequestHandler().getHttpPromise('payment/'+applicationId+'/'+isPaid+ '.json', $http, $q, $log);
	};
	
	
	return service;
};

uperApp.factory("uperService", UperService);
