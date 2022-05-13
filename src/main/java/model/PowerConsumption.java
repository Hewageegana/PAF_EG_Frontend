package model;

import java.sql.*;  

public class PowerConsumption {
	
	
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg_online_system", "root", "rusiru123");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}	

	//Insert
public String InsertPowerConsumptionDetails(String userID, String account_Number, String cus_name, String units, String days,String generated_date) {
 
	String output = "";
	try {
		Connection con = connect();
		
		if (con == null)
		{return  "Error while connecting to the database for inserting.";}
		
		// create a prepared statement
		
		String query = "  insert into power_consumption (`idpower_consumption`,`userID`,`account_Number`,`cus_name`,`units`,`days`,`generated_date`)" + " values (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, userID);
		 preparedStmt.setString(3, account_Number);
		 preparedStmt.setString(4, cus_name); 
		 preparedStmt.setString(5, units); 
		 preparedStmt.setString(6, days);
		 preparedStmt.setString(7, generated_date);
	
		 
		// execute the statement
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
	}
	catch (Exception e) {
		 output = "Error while inserting the Power Consumption."; 
		 System.err.println(e.getMessage()); 
	}
	
	return output;
	}

//Retrieve
public String readPwerConsumption() {
	
	String output = "";
	
	try {
		
		Connection con = connect();
		
		if(con == null) {
			return "Error while connecting to the database for reading.";
		}
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'>"
		 		+ "<tr>"
		 		+ "<th>Customer ID</th>"
		 		+ "<th>Account Number</th>" +
		 		  "<th>CustomerName</th>" + 
		 		  "<th>Units</th>" +
		 		  "<th>Days</th>" +	
		 		 "<th>Generated Date</th></tr>";
		 
		 String query = "select * from power_consumption"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while(rs.next()) {
			 
			 String userID = rs.getString("userID");
			 String account_Number = rs.getString("account_Number"); 
			 String cus_name = rs.getString("cus_name"); 
			 String units = rs.getString("units"); 
			 String days = rs.getString("days"); 
			 String generated_date = rs.getString("generated_date");
			
			 
			 // Add into the HTML table
			 output += "<tr><td>" + userID + "</td>"; 
			 output += "<td>" + account_Number + "</td>";
			 output += "<td>" + cus_name + "</td>"; 
			 output += "<td>" + units + "</td>"; 
			 output += "<td>" + days + "</td>";
			 output += "<td>" + generated_date + "</td>";
			 
			 
			 // buttons
			
		 }
		 con.close(); 
		 // Complete the html table
		 output += "</table>";
	}
	catch(Exception e)
	{
		 output = "Error while reading the customer."; 
		 System.err.println(e.getMessage()); 
	}
	
	return output;
	}

//Update

public String updateConsumption(String userID, String account_Number, String cus_name, String units, String days,String generated_date) {
	
	String output = "";
	
	try {
		Connection con = connect();
		
		if (con == null)
		{return  "Error while connecting to the database for updating.";}
		
		// create a prepared statement
		String query = "  UPDATE power_consumption SET account_Number=?,cus_name=?,units=?,days=?,generated_date=? where userID=?"  ;
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 // binding values
		  
		 preparedStmt.setString(1, account_Number); 
		 preparedStmt.setString(2, cus_name); 
		 preparedStmt.setString(3, units);
		 preparedStmt.setString(4, days);
		 preparedStmt.setString(5, generated_date);
		 preparedStmt.setString(6,userID); 
		 
		 
		// execute the statement
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successful"; 
	}
	catch (Exception e) {
		 output = "Error while Update the Customer."; 
		 System.err.println(e.getMessage()); 
	}
	
	return output;
	}

//Delete
public String deleteConsume(String userID) {
	 String output = ""; 
	 try {
		 Connection con = connect(); 
		 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from power_consumption where userID=?"; 
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 output = "Deleted successfully"; 
	 }
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the customer."; 
		 System.err.println(e.getMessage()); 
	 } 
	 
	 return output;
}
}
