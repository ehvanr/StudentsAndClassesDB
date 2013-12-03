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
	 * <br /><br />
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
	 * getClassIDs
	 * <br /><br />
	 * Returns a pre-formatted ArrayList of type String of all 
	 * classes that the user has currently already taken.
	 *
	 * @return String classIDs
	*/
	public ArrayList<String> getClassIDs(){
		String classIDQuery = "SELECT ClassIDs FROM Students WHERE username='" + username + "'";
		ResultSet rs = executeQuery(classIDQuery);
		ArrayList<String> classIDs = new ArrayList<String>();
		
		try{
			String unparsedClassIDs = rs.getString(1);
			
			// Parse the classIDs (CSV)
			String[] splitParsed = unparsedClassIDs.split(",");
			
			for(String tempS : splitParsed){
				tempS = tempS.trim();
				classIDs.add(tempS);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return classIDs;
	}
	
	/**
	 * getMajorID
	 * <br /><br />
	 * Returns the users MajorID
	 *
	 * @return String majorID
	 **/
	public String getMajorID(){
		String majorIDQuery = "SELECT MajorID FROM Students WHERE username='" + username + "'";
		ResultSet rs = executeQuery(majorIDQuery);
		
		try{
			// Move the cursor!
			rs.next();
			majorID = rs.getString(1);
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return majorID;
	}
	
	/**
	 * getMinorID
	 * <br /><br />
	 * Returns the users MinorID
	 *
	 * @return String minorID
	 **/
	public String getMinorID(){
		String minorIDQuery = "SELECT MinorID FROM Students WHERE username='" + username + "'";
		ResultSet rs = executeQuery(minorIDQuery);
		
		try{
			// Move the cursor!
			rs.next();
			minorID = rs.getString(1);
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return minorID;
	}
}