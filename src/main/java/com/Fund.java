package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


	
	//\\\\\\\\\\\\\\\\\\\\\\\\\\\\read Funds\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public String readFund() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Fund Type</th>"
					+ "<th>Fund Description</th>"
					+ "<th>Fund Amount</th>"
					+ "<th>Fund Issued Date</th>"
					+ "<th>Fund Issued By</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";

			String query = "select * from fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundId"));
				String fundType = rs.getString("fundType");
				String funddescription = rs.getString("description");
				String fundAmount = Integer.toString(rs.getInt("fundAmount"));
				String fundIssuedDate = rs.getString("issuedDate");
				String fundIssuedBy = rs.getString("issuedBy");
				
				// Add into the html table
				output += "<tr>"
						+ "<td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" +fundID+"'>" +fundType +"</td>";
				output += "<td>" + funddescription + "</td>";
				output += "<td>" + fundAmount + "</td>";
				output += "<td>" + fundIssuedDate + "</td>";
				output += "<td>" + fundIssuedBy + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundId='" + fundID + "'></td>"
						+ "</tr>";
			}
			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading Fund detailss.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\insert Funds\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public String insertFund(String fundType, String description, String fundAmount, String issuedDate, String issuedBy) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into fund(`fundId`, `fundType`, `description`, `fundAmount`, `issuedDate`, `issuedBy`) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fundType);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(fundAmount));
			preparedStmt.setString(5, issuedDate);
			preparedStmt.setString(6, issuedBy);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "status:error ,data:Error while inserting the Funds details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Update_Fund\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public String updateFund(String fundId,String fundType, String description, String fundAmount, String issuedDate, String issuedBy) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fund SET fundType=?,description=?,fundAmount=?,issuedDate=?,issuedBy=? WHERE fundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, fundType);
			preparedStmt.setString(2, description);
			preparedStmt.setInt(3, Integer.parseInt(fundAmount));
			preparedStmt.setString(4, issuedDate);
			preparedStmt.setString(5, issuedBy);
			preparedStmt.setInt(6, Integer.parseInt(fundId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "status:error data Error while updating the Fund details.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	//***********************************delete Funds****************************************
	public String deleteFund(String fundId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fund where fundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "status:error data :Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
