package com.lcg.userloginservice.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userLogin")
public class UserLoginService {

	@RequestMapping("/{userEmail}/{userPass}")
	public boolean authenticateUser(@PathVariable("userEmail") String userEmail,@PathVariable("userPass") String userPass){
		
		boolean isValidUser = false;
		//System.out.println(userEmail + " userPass" +userPass+" :::::Authenticate user service");
		
		//Connect to DB and Query
		 String connectionUrl =
	                "jdbc:sqlserver://database-1.ctfzetvuk3po.us-east-2.rds.amazonaws.com:1433;"
	                + "database=ScheduleEx;"
	                + "user=admin;"
	                + "password=december;";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	                Statement statement = connection.createStatement();) {

	            // Create and execute a SELECT SQL statement.
	            //String selectSql = "SELECT * from dbo.Users where Email='" +userEmail+"'";
	        	String selectSql = "SELECT * from dbo.Users where Email='JLevis@TestUniversity.com'";
	            resultSet = statement.executeQuery(selectSql);
	            //System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));

	            // Print results from select statement
	            while (resultSet.next()) {
	                System.out.println(userPass+"  "+resultSet.getString(5));
	                if(resultSet.getString(5).equals(userPass)){
	                	isValidUser=true;
	                	 break;
	    	        }
	                }
	               
	            }
	            
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return isValidUser;
	}

}
