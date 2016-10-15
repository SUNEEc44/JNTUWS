<HTML>
<HEAD>
<link href="TabbedTraffic.css" rel="stylesheet" type="text/css"/>
<TITLE>Traffic Analysis</TITLE>
<script type="text/javascript">
	function showMe(id) {
		hide('show-me-traffic');
		hide('show-me');
		hide('show-me-max');
		document.getElementById(id).style.display = 'block';
	}
	function clear() {
		document.getElementById('city').value = "";
		document.getElementById('acity').value = "";
		document.getElementById('ccity').value = "";
		document.getElementById('clocality').value = "";
		document.getElementById('cname').value = "";
	}
	function submitPage(tfvalue)
	{
		document.getElementById('tfAnalysis').value = tfvalue;
		document.submit();
	}
	
	function hide(id) {
		document.getElementById(id).style.display = 'none';
	}
	
	function openCity(evt, cityName) {
	    // Declare all variables
	    var i, tabcontent, tablinks;

	    // Get all elements with class="tabcontent" and hide them
	    tabcontent = document.getElementsByClassName("tabcontent");
	    for (i = 0; i < tabcontent.length; i++) {
	        tabcontent[i].style.display = "none";
	    }

	    // Get all elements with class="tablinks" and remove the class "active"
	    tablinks = document.getElementsByClassName("tablinks");
	    for (i = 0; i < tablinks.length; i++) {
	        tablinks[i].className = tablinks[i].className.replace(" active", "");
	    }

	    // Show the current tab, and add an "active" class to the link that opened the tab
	    document.getElementById(cityName).style.display = "block";
	    evt.currentTarget.className += " active";
	    clear();
	}
</script>

</HEAD>


<BODY>
 <H1>Traffic Analysis</H1>
	<FORM ACTION="traffic" METHOD="post" onload="clear()">
		<ul class="tab">
			<li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'TrafficAnalysis')">TRAFFIC ANALYSIS</a>
			</li>
			<li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'AccidentsZone')">ACCIDENTS ZONE</a>
			</li>
			<li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'Challans')"> CHALLANS</a>
			</li>
		</ul>

		<div id="TrafficAnalysis" class="tabcontent">
			<h3>Traffic Consumed Zones</h3>
			<p>Enter City:<input type="text" id="city" name="city" /></p>
			<BR>
			<INPUT TYPE="submit" VALUE="Submit" onclick="submitPage('traffic_consumed_analysis')"/>
		</div>

		<div id="AccidentsZone" class="tabcontent">
			<h3>Accidents Zone</h3>
			<p>Enter City:<input type="text" id="acity" name="acity" />.</p>
			<INPUT TYPE="submit" VALUE="Submit" onclick="submitPage('traffic_accidents')"/>
		</div>

		<div id="Challans" class="tabcontent">
			<h3>Challans</h3>
			<p>
			Enter city:<input type="text" id="ccity" name="ccity"/>
              <BR>OR<br>
              Enter locality:<input type="text" id="clocality" name="clocality" />
              <BR>OR<br>
              Enter CustomerName:<input type="text" id="cname" name="cname" />
              </p>
              <INPUT TYPE="submit" VALUE="Submit" onclick="submitPage('traffic_challan')"/>
		</div>
		<input type="hidden" id="tfAnalysis" name="tfAnalysis" value="">
	</FORM>
</BODY>