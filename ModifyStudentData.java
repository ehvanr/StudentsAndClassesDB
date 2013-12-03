import java.sql.*;

public class ModifyStudentData{
	static Connection connection;
	static PreparedStatement prepStatement;
	
	/**
	 * Default Constructor
	 **/
	public ModifyStudentData(){
		
	}

	private boolean updateQuery(String query){
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = "jdbc:odbc:Students";
            
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
	
	public void updateStudent(String oldStudentID, String newStudentID, String studentName, String classIDs, String MajorID, String MinorID){
		String updateStudentQuery = "UPDATE Students SET StudentID='" + newStudentID + "', StudentName='" + studentName + "', ClassIDs='" + classIDs + "', MajorID='" + MajorID + "', MinorID='" + MinorID + "' WHERE StudentID='" + oldStudentID + "'";
		
		if(updateQuery(updateStudentQuery)){
			// Success
		}else{
			// Failure
		}
	}
	
	public void deleteStudent(String studentID){
		String deleteStudentQuery = "DELETE FROM Students WHERE StudentID='" + studentID + "'";
		
		if(updateQuery(deleteStudentQuery)){
			// Success
		}else{
			// Failure
		}
	}
	
	// Verify that no such student exists
	public void addStudent(String studentID, String studentName, String classIDs, String MajorID, String MinorID){
		String addStudentQuery = "INSERT INTO Students VALUES ('" + studentID + "', '" + studentName + "', '" + classIDs + "', '" + MajorID + "', '" + MinorID + "')";
		if(updateQuery(addStudentQuery)){
			// Success
		}else{
			// Failure
		}
	}
}