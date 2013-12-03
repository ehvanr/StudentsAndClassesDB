import java.sql.*;

public class ModifyCourseData{
	static Connection connection;
	static PreparedStatement prepStatement;
	
	/**
	 * Default Constructor
	 **/
	public ModifyCourseData(){
		
	}
	
	private boolean updateQuery(String query){
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = "jdbc:odbc:Courses";
            
			connection = DriverManager.getConnection(database,"",""); 
			prepStatement = connection.prepareStatement(query);
			prepStatement.executeUpdate();
			connection.close();
			
			return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error!");
			return false;
        }
		
	}
	
	// This assumes that requirements have been verified
	public void updateClassesTable(String classID, String className, String creditValue, String requirements){
		String updateClassesQuery = "UPDATE Classes SET ClassID='" + classID + "', ClassName='" + className + "', CreditValue='" + creditValue + "', Requirements='" + requirements + "' WHERE classID='" + classID + "'";
		
		if(updateQuery(updateClassesQuery)){
			// Woo successful
		}else{
			// Booo failure
		}
		
	}
	
	public void removeClass(String classID){
		String removeClassQuery = "DELETE FROM Classes WHERE ClassID='" + classID + "'";
		
		if(updateQuery(removeClassQuery)){
			// Success
		}else{
			// Failure
		}
	}
	
	public void addClass(String classID, String className, String creditValue, String requirements){
		String addClassQuery = "INSERT INTO Classes VALUES ('" + classID + "', '" + className + "', '" + creditValue + "', '" + requirements + "')";
		
		if(updateQuery(addClassQuery)){
			// Success
		}else{
			// Failure
		}
	}
}