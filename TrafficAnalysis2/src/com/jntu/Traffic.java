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
public class Traffic extends HttpServlet {
	
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
		String option = request.getParameter("tfAnalysis");
		
		if(option.equalsIgnoreCase("traffic_consumed_analysis"))
		{
			String city = request.getParameter("city");
			
			final String query="select SENSORNAME,CITY,LOCALITY,VEHICLECOUNT,ALLOWEDCOUNT,TRAFFIC_FLOW from traffic_analysis where city='"+city+"'";

			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			out.println("<title>Network Usage Statistics</title>");

			out.println("<H1>Traffic information</H1>");
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>SENSORNAME</th>" +
						"<th>CITY</th>" +
						"<th>LOCALITY</th>" +
						"<th>VEHICLECOUNT</th>" +
						"<th>ALLOWED COUNT</th>" +
						"<th>TRAFFIC_FLOW</th>" +
					"</tr>");
			while(rs.next()){

			out.println("<tr>" +
					"<td>"+rs.getString(1)+"</td>" +
					"<td>"+rs.getString(2)+"</td>" +
					"<td>"+rs.getString(3)+"</td>" +
					"<td>"+rs.getString(4)+"</td>" +
					"<td>"+rs.getString(5)+"</td>" +
					"<td>"+rs.getString(6)+"</td>" +
					"</tr>");
			}
			out.println("</table>");
			
		}else if(option.equalsIgnoreCase("traffic_accidents"))
		{
			String city=request.getParameter("acity");
			
			final String query="select city,locality,sum(accidents) from traffic_accidents_info where city ='"+city+"' group by city,locality";

			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			out.println("<title>Accidents Statistics</title>");

			out.println("<H1>Accidents Statistics</H1>");
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>CITY</th>" +
						"<th>LOCALITY</th>" +
						"<th>Accidents/year</th>" +
					"</tr>");
			while(rs.next()){

			out.println("<tr>" +
					"<td>"+rs.getString(1)+"</td>" +
					"<td>"+rs.getString(2)+"</td>" +
					"<td>"+rs.getString(3)+"</td>" +
					"</tr>");
			}
			out.println("</table>");
	
			
		}else if(option.equalsIgnoreCase("traffic_challan"))
		{
			final String city=request.getParameter("ccity");
			final String locality=request.getParameter("clocality");
			final String customerName=request.getParameter("cname");
			String query=null;
			
			if(city!=null && !city.trim().isEmpty())
			{
				query="select city,locality,count(customer_id), sum(amount),paid from traffic_customer_challan where city='"+city+"' group by city,locality,paid order by paid desc";
			}
			else if(locality!=null && !locality.trim().isEmpty())
			{
				query="select city,locality,customer_name,sum(amount),paid from traffic_customer_challan where locality='"+locality+"' group by city,locality,customer_name,paid order by paid desc";
			}
			else{
				query="select city,locality,customer_name,sum(amount),paid from traffic_customer_challan where customer_name='"+customerName+"' group by city,locality,customer_name,paid order by paid desc";
			}
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			out.println("<title>Traffic Challan Statistics</title>");
			
			out.println("<H1>Traffic Challan Stats</H1>");

			if(city!=null && !city.trim().isEmpty())
			{
			
			out.println("<table border='1'>" +
					"<tr>" +
						"<th>CITY</th>" +
						"<th>LOCALITY</th>" +
						"<th>Total Customers</th>" +
						"<th>Total Amount</th>" +
						"<th>PAID</th>" +
					"</tr>");
			}else{
				out.println("<table border='1'>" +
						"<tr>" +
							"<th>CITY</th>" +
							"<th>LOCALITY</th>" +
							"<th>CustomerName</th>" +
							"<th>Total Amount</th>" +
							"<th>PAID</th>" +
						"</tr>");
				
			}
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
