'use strict';


var uperApp = angular.module("uperApp", ['ui.bootstrap', 'ngRoute', 'ui.utils']);
 
uperApp.config(['$routeProvider',
    function($routeProvider) {
	    $routeProvider.when('/demo', {
	        templateUrl: 'resources/js/partials/demo.html' 
	    }).when('/adminMenu', {
	        templateUrl: 'resources/js/partials/adminMenu.html' 
	    }).when('/patientDetail', {
	        templateUrl: 'resources/js/partials/patientDetail.html' 
	    }).when('/patientDetail/:patientId', {
	        templateUrl: 'resources/js/partials/patientDetail.html' 
	    }).when('/getApplication/:stateFileNumber', {
	        templateUrl: 'resources/js/partials/applicationDetail.html' 
	    }).when('/userList', {
	        templateUrl: 'resources/js/partials/userList.html' 
	    }).otherwise({
	        redirectTo: '/patientDetail' 
	    });
 }]);
 
 