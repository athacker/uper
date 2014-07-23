
<script type='text/javascript' src="resources/js/lib/jquery.min.js"></script>


<script>
$( document ).ready(){

	
	
	
		$("a.detailsButton").click(function(event){
		    event.preventDefault();
		 	event.stopPropagation();
		 	var rowToToggle=  $(this).data("row"); 
		    $('rowToToggle').toggle();
		   
		}  );

alert("Done");

});
</script>





<div id="wm-report-facility-requirements" class="">
<h3 class="tableTitle">Monitoring Requirements By Facility</h3>  
      <table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Facility Details</th>
    </tr>
  </thead>
  <tbody>
   
   
    <tr>
      <td>PF001</td>
      <td>Iron Canyon</td>
      <td><a href="#" class="detailsButton" data-row="PF001">View Details</a></td>
    </tr>
   
   
    <tr class="details" id="PF001"/>
    <td colspan="100">
      <div>
      <table>
      <thead>
          <tr>
            <th>Name</th>
            <th>Sample Count</th>
            <th>Type</th>
            <th>Frequency</th>
            <th>Last Sample</th>
            <th>Next Sample Due</th>
        </tr>
        </thead>
      <tbody> 
          <tr>
            <td>Nitrate</td>
            <td>1</td>
            <td>Routine</td>
            <td>Quarter</td>
            <td>1/17/14</td>
            <td>4/1/14 - 6/30-14</td>
        </tr>
        
        <tr>
            <td>Pesticides</td>
            <td>1</td>
            <td>Routine</td>
            <td>Quarter</td>
            <td>1/17/14</td>
            <td>4/1/14 - 6/30-14</td>
        </tr>  
          
      </tbody>
      </table>
      
      
      
      
      <div class="clearfix"></div>
      </div><!--end of sub table details-->
     </td>
     </tr>
    
   
    
     <tr class="">
    <td colspan="100">Total Facilities: 3</td>
    </tr>
  </tbody>
</table>
</div>

 

