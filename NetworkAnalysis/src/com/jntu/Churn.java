package com.jntu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Churn
 */
@WebServlet("/Churn")
public class Churn extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("MySQL Connect Example.");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";;
		String dbName = "project";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "password";


		Statement st;
		try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		System.out.println("Connected to the database");
		String option = request.getParameter("nwanalysis");
		
		if(option.equalsIgnoreCase("churn_analysis"))
		{
			String locality = request.getParameter("locality");
			
			final String query="select name,city,locality,churn_category,NW_BAND_CONS_AVG,billing_category,amount from network_analysis" +
					" where locality='"+locality+"' and churn_category='CHURN'";

			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			out.println("<title>Network Usage Statistics</title>");

			out.println("<H1>Customer N/W Bandwidth Churn information</H1>");
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>NAME</th>" +
						"<th>CITY</th>" +
						"<th>LOCALITY</th>" +
						"<th>N/w Bandwidth Usage</th>" +
						"<th>Average N/w Bandwidth Consumption</th>" +
						"<th>BillUsage</th>" +
						"<th>Average Spend Amount(pm)</th>" +
					"</tr>");
			while(rs.next()){

			out.println("<tr>" +
					"<td>"+rs.getString(1)+"</td>" +
					"<td>"+rs.getString(2)+"</td>" +
					"<td>"+rs.getString(3)+"</td>" +
					"<td>"+rs.getString(4)+"</td>" +
					"<td>"+rs.getString(5)+"</td>" +
					"<td>"+rs.getString(6)+"</td>" +
					"<td>"+rs.getString(7)+"</td>" +
					"</tr>");
			}
			out.println("</table>");
			
		}else if(option.equalsIgnoreCase("customer_churn_analysis"))
		{
			String customerName=request.getParameter("customername");
			
			final String query="select name,city ,locality, churn_category,NW_BAND_CONS_AVG,billing_category,amount from network_analysis where name='"+customerName+"'";

			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			out.println("<title>Network Usage Statistics</title>");

			out.println("<H1>Information about the customer's information</H1>");
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>NAME</th>" +
						"<th>CITY</th>" +
						"<th>LOCALITY</th>" +
						"<th>N/w Bandwidth Usage</th>" +
						"<th>Average N/w Bandwidth Consumption</th>" +
						"<th>BillUsage</th>" +
						"<th>Average Spend Amount(pm)</th>" +
					"</tr>");
			while(rs.next()){

			out.println("<tr>" +
					"<td>"+rs.getString(1)+"</td>" +
					"<td>"+rs.getString(2)+"</td>" +
					"<td>"+rs.getString(3)+"</td>" +
					"<td>"+rs.getString(4)+"</td>" +
					"<td>"+rs.getString(5)+"</td>" +
					"<td>"+rs.getString(6)+"</td>" +
					"<td>"+rs.getString(7)+"</td>" +
					"</tr>");
			}
			out.println("</table>");
	
			
		}else if(option.equalsIgnoreCase("MAX_NW_CONSUMPTION"))
		{
			final String city=request.getParameter("city").toUpperCase();
			final String query="select locality, churn_category,billing_category,sum(amount) as total,count(1) as count from network_analysis " +
								"where city='"+city+"' GROUP BY locality,churn_category,billing_category order by billing_category asc";
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			out.println("<title>Network Usage Statistics</title>");
			
			out.println("<H1>Following is the list of BandWidth Usages according to locaity</H1>");
			
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>Locality</th>" +
						"<th>N/w Bandwidth Usage</th>" +
						"<th>BillUsage</th>" +
						"<th>Total Amount</th>" +
						"<th>Customer Count</th>" +
					"</tr>");
			
			while(rs.next())
			{
			out.println("<tr>" +
							"<td>"+rs.getString(1)+"</td>" +
							"<td>"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(3)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+rs.getString(5)+"</td>" +
						"</tr>");
			}
			
			out.println("</table>");
			
		}
		
		System.out.println("Disconnected from database");
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}
