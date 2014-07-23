<!DOCTYPE HTML >
<html id="ng-app" data-ng-app="uperApp" data-ng-controller="mainController" data-ng-cloak >
<head>
	<meta charset="UTF-8">
	
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel='stylesheet' id='normalize-css'  href="resources/js/partials/css/normalize.css" type='text/css' media='all' />
	<link rel='stylesheet' id='boot-css'  href="resources/js/partials/css/bootstrap.css" type='text/css' media='all' />
 	<link rel='stylesheet' id='style-css'  href="resources/js/partials/css/style.css" type='text/css' media='all' />
 	<link rel='stylesheet' id='style-css'  href="resources/js/partials/css/style-forms.css" type='text/css' media='all' />
 	
 	
	<title>UPER - Utah Plant Extract Registry</title>
 
  	<script>
		document.createElement('ng-pluralize');
		document.createElement('ng-include');
		document.createElement('ng-view');
		document.createElement('header');
		document.createElement('footer');
	</script>
 

 
	<script src="resources/js/lib/html5.js" type="text/javascript"></script>
  	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	 


</head>






<body>

 

 

<div header></div>
 
<div ng-view></div ng-view>

<div class="messageBackground">
<div data-ng-repeat="message in messages" data-ng-animate="'fade'"  class="messageCenter {{messageStyle}}" >
	<h3>{{ message.title }}</h3>
	<p>{{ message.text }}</p>
</div>
</div>
 

 <div footer></div>
 





<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
 
<script src="resources/js/lib/angular-1.2.11/angular.min.js"></script>
<script src="resources/js/lib/angular-1.2.11/angular-route.min.js"></script>
<script type="text/javascript" src="resources/js/lib/ui-utils-0.1.1/ui-utils.min.js"></script>
<script type="text/javascript" src="resources/js/lib/underscore.js"></script>
<script type="text/javascript" src="resources/js/lib/jquery-class.js"></script>
<script type="text/javascript" src="resources/js/lib/RequestHandler.js"></script>
<script type="text/javascript" src="resources/js/lib/ResponseHandler.js"></script>

 
<script type="text/javascript" src="resources/js/app.js"></script> 

<script type="text/javascript" src="resources/js/controllers/AdminController.js"></script>
<script type="text/javascript" src="resources/js/controllers/ApplicationController.js"></script>
<script type="text/javascript" src="resources/js/controllers/DemoController.js"></script> 
<script type="text/javascript" src="resources/js/controllers/MainController.js"></script>
<script type="text/javascript" src="resources/js/controllers/PatientController.js"></script>

<script type="text/javascript" src="resources/js/directives/header.js"></script>
<script type="text/javascript" src="resources/js/directives/footer.js"></script>
<script type="text/javascript" src="resources/js/directives/onBlur.js"></script>
<script type="text/javascript" src="resources/js/directives/dateRange.js"></script>
<script type="text/javascript" src="resources/js/directives/activepage.js"></script>
<script type="text/javascript" src="resources/js/directives/capitalizefirst.js"></script>
<script type="text/javascript" src="resources/js/services/UperService.js"></script>
<script type="text/javascript" src="resources/js/services/DemoService.js"></script>
 


<script type='text/javascript' src="resources/js/lib/modernizr-2.6.2.min.js"></script>

<script type='text/javascript' src="resources/js/lib/jquery.easing.min.js"></script>
<script type='text/javascript' src="resources/js/lib/plugins.js"></script>
<script type='text/javascript' src="resources/js/lib/jquery.cookie.js"></script>
<script type='text/javascript' src="resources/js/lib/bootstrap/js/bootstrap.js"></script>
<script type='text/javascript' src="resources/js/lib/bootstrap/js/ui-bootstrap-tpls-0.11.0.js"></script>

</body>

<script>
var $j = jQuery.noConflict();

$j( document ).ready(function() {
  window.XMLHttpRequest = window.XMLHttpRequest || (function() {
        return new window.ActiveXObject("Microsoft.XMLHTTP");
    });
   
});
 
</script>
 
<div ng-init="release='<%@include file="version.jsp" %>' "/><br>
</html>