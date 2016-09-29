<HTML>
    <HEAD>
        <TITLE>Network Analysis</TITLE>
         <script type="text/javascript">
       		 function showMe(id) {
       			 hide('show-me-churn');
       			hide('show-me');
       			hide('show-me-max');
       			 document.getElementById(id).style.display = 'block'; }
       		 function hide(id) { document.getElementById(id).style.display = 'none'; }
      </script>
    </HEAD>
 
    <BODY>
        <H1>Network Churn Analysis</H1>
        <FORM ACTION="churn" METHOD="post">
             <INPUT TYPE="radio" NAME="nwanalysis" VALUE="churn_analysis" onclick="showMe('show-me-churn')"">
             CHURN ANALYSIS
              <div id='show-me-churn' style='display:none'>Enter Locality:<input type="text" id="locality" name="locality" /></div>
            <BR>
            <INPUT TYPE="radio" NAME="nwanalysis" VALUE="customer_churn_analysis" onclick="showMe('show-me')">
             CUSTOMER CHURN ANALYSIS
             <div id='show-me' style='display:none'>Enter Customer Name:<input type="text" id="customername" name="customername" /></div>
            <BR>
            <INPUT TYPE="radio" NAME="nwanalysis" VALUE="MAX_NW_CONSUMPTION" onclick="showMe('show-me-max')">
            SHOW NETWORK BANDWIDTH USAGE STATS
              <div id='show-me-max' style='display:none'>Enter City:<input type="text" id="city" name="city" /></div>
            <BR>
            <INPUT TYPE="submit" VALUE="Submit">
        </FORM>
    </BODY>
</HTML>