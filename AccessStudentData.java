/**
 * Unlike the AccessCourseData class, this is a per-student object class.
 * AccessCourseData is a more general object where you only have to use one
 * to do most everything. 
 *
 * This class you need to define per student.
 *
 **/

import java.sql.*;
import java.util.ArrayList;

public class AccessStudentData{
	private String username;
	private String[] classIDs;
	private String majorID;
	private String minorID;
	
	static Connection connection;
	static Statement statement;
	
	/**
	 * AccessStudentData Constructor
	 * 
	 * @param String username
	 **/
	public AccessStudentData(String username){
		this.username = username;
	}
	
	/**
	 * executeQuery
	 * 
	 * This method is for opening and executing the query passed along to it. 
	 * This method does not close the Connection that it opens, it must be closed 
	 * via the methods that called it. This is because we return the ResultSet. 
	 *
	 * @param 	String		query
	 * @return 	ResultSet 	rs
	 **/
	private ResultSet executeQuery(String query){
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = "jdbc:odbc:Students";
            
			connection = DriverManager.getConnection(database,"",""); 
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			// We return the ResultSet so the individual methods deal with the information.
			// Don't forget! We need to close the connection once we're done in the method we return to.
			return rs;
			
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error!");
        }
		
		return null;
	}
	
	/**
	 * getClassIDs - Accesses the database to return an array of classIDs
	 *
	 * @return String classIDs
	*/
	public ArrayList<String> getClassIDs(){
		String classIDQuery = "SELECT ClassIDs FROM Students WHERE username='" + username + "'";
		ArrayList<String> classIDs = new ArrayList<String>();
		
		try{
			String unparsedClassIDs = rs.getString(1)
			
			// Parse the classIDs (CSV)
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return classIDs;
	}
	
	public String getMajorID(){
		return majorID;
	}
	
	public String getMinorID(){
		return minorID;
	}
}