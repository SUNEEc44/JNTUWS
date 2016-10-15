<HTML>
    <HEAD>
        <TITLE>Traffic Analysis</TITLE>
         <script type="text/javascript">
       		 function showMe(id) {
       			 hide('show-me-traffic');
       			hide('show-me');
       			hide('show-me-max');
       			 document.getElementById(id).style.display = 'block'; }
       			 function clear() 
       			 {
            			document.getElementById('ccity').value= "";
            			document.getElementById('clocality').value= "";
            			document.getElementById('cname').value= "";
       		 	}
       		 function hide(id) { document.getElementById(id).style.display = 'none'; }
      </script>
    </HEAD>
 
    <BODY>
       <H1>Traffic Analysis</H1>
        <FORM ACTION="traffic" METHOD="post" onload="clear()">
             <INPUT TYPE="radio" NAME="tfAnalysis" VALUE="traffic_consumed_analysis" onclick="showMe('show-me-traffic')">
             TRAFFIC ANALYSIS
              <div id='show-me-traffic' style='display:none'>Enter City:<input type="text" id="city" name="city" /></div>
            <BR>
            <INPUT TYPE="radio" NAME="tfAnalysis" VALUE="traffic_accidents" onclick="showMe('show-me')">
             ACCIDENTS ZONE
             <div id='show-me' style='display:none'>Enter City:<input type="text" id="acity" name="acity" /></div>
            <BR>
            <INPUT TYPE="radio" NAME="tfAnalysis" VALUE="traffic_challan" onclick="showMe('show-me-max')">
            CHALLANS
              <div id='show-me-max' style='display:none'>
              Enter city:<input type="text" id="ccity" name="ccity"/>
              <BR>OR<br>
              Enter locality:<input type="text" id="clocality" name="clocality" />
              <BR>OR<br>
              Enter CustomerName:<input type="text" id="cname" name="cname" />
				</div>
            <BR>
            <INPUT TYPE="submit" VALUE="Submit">
        </FORM>
    </BODY>
</HTML>