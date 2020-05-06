package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	
String output="";
	
	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcaresystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//  spring.datasource.url=jdbc:mysql://localhost:3301/student?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
			// For Testing
			System.out.println("Successfully Connected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Unsuccessful!!!!");
		}
		return con;

	}
	
	public String addHospitals (String name, String no, String address, String email)
	{
		
		
		try {
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Error connecting to the Database";
			}
	//	create prepared statement
			String query = "INSERT INTO `hospital`(`hospitalID`, `hospitalName`, `hospitalphoneNo`, `hospitalAddress`, `hospitalEmail`) "
					+ "VALUES (?,?,?,?,?)";
	//binding values			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, Integer.parseInt(no));
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, email);
//execute the statement			
			preparedStatement.execute();
			con.close();
			preparedStatement.close();
			//output = "Inserted Successfully";
			String newHospitals = readHospital();
			output="{\"status\":\"success\"}";
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			//output="Error inserting data";
			output="{\"status\":\"error\",\"data\": \"Error while inserting.\"}";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	public String readHospital() {
		String output = " ";
		
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to database for reading.";
			}
			//Prepare the html table to displayed
			//hospitalID`, `hospitalName`, `hospitalphoneNo`, `hospitalAddress`, `hospitalEmail
			output="<table class=\"table\" border =\"1\">"
					+ "<tr><th>Name</th><th>Phone Number</th><th>Address</th><th>E-mail</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query="select * from hospital";
			Statement statement = con.createStatement();
			ResultSet set = statement.executeQuery(query);
		//iterate through the rows in the result set	
			while (set.next()) {
				
				String id = Integer.toString(set.getInt("hospitalID"));
				String name = set.getString("hospitalName");
				String no = Integer.toString(set.getInt("hospitalphoneNo"));
				String address = set.getString("hospitalAddress");
				String email = set.getString("hospitalEmail");
				
		//Add into the html table	
				output +="<tr><td><input id='hidHospitalIDUpdate'"
						+ "name='hidHospitalIDUpdate' type='hidden'"
						+ "value='"+id+"'>"+id+"</td>";						
				output += "<tr><th>" + name +"</th>";
				output += "<th>" + no + "</th>";
				output += "<th>" + address + "</th>";
				output += "<th>" + email + "</th>";
		//buttons		
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' "
						+ "data-medicineid='" + id + "'>" + "</td></tr>"; 
					 }
				/*output += "<td><input type=\"button\" name=\"btnUpdate\" value=\"update\"></td>"
						+ "<td><form method=\"post\" action=\"doctor.jsp\">"
						+ "<input name=\"btnRemove\" value=\"remove\" type=\"submit\">"
						+ "<input name=\"id\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>" ;
			}*/
			
			con.close();
			//Complete the html table
			
			output +="</table>";
			
			statement.close();
			System.out.println("successfully print all data...");
					
			
		} catch (Exception e) {
			// TODO: handle exception
			output = "Cannot read the data";
			System.err.println(e.getMessage());
	
		}
		
		return output;
	}
	
	public String deleteHospital(String id)
	{
		String output="";
		
		try {
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database";
			}
			//create prepared statement
			String query = "delete from hospital where hospitalID =?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			//building values	
			preparedStatement.setInt(1, Integer.parseInt(id));
			//execute the statement
			preparedStatement.execute();
			con.close();
			
			String newHospitals = readHospital();    
			   output = "{\"status\":\"success\"}";   
			
		} catch (Exception e) {
			// TODO: handle exception
			
			output = "{\"status\":\"error\", \"data\":\"Error while deleting.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String updateHospital(String ID, String name, String no, String address, String email)
	{
		String output ="";
		try {
			
			Connection con = connect();
			
			if(con == null) {
			
				return "Error while connecting to the database";
			}
			//create prepared statement
			String query = "UPDATE `hospital` "
							+ "SET `hospitalName`=? ,`hospitalphoneNo`=? ,`hospitalAddress`=? ,`hospitalEmail`=? "
							+ "WHERE `hospitalID`= ?";
					
					
					/*"UPDATE `hospital` "
					+ "SET `dName`=?,`dSpecialization`=?,`dAddress`=?,`dEmail`=?,`dFee`=?,`dWHospital`=?"
					+ " WHERE `ID`= ?";*/
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
		//building values	
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, Integer.parseInt(no));
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, email);
			preparedStatement.setInt(5, Integer.parseInt(ID));
		//execute the statement	
			preparedStatement.execute();
			preparedStatement.close();
			con.close();
			
			String newHospitals =readHospital();			
			output ="{\"status\":\"success\",\"data\":\""+newHospitals+"\"}";
			
			
		} catch (Exception e) {
			// TODO: handle exception
			output =" {\"status\":\"success\",\"data\":\"Error while updating the hospital.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
		
	
				
				
				

			

