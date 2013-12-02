import java.sql.*;
import java.util.ArrayList;

public class AccessStudentData{
	private String username;
	private String[] classIDs;
	private String majorID;
	private String minorID;
	
	/*
		Parameterized constructor AccessStudentData
		@param String username - username of student to be accessed
	*/
	public AccessStudentData(String username){
		this.username = username;
	}
	/*
		getClassIDs - Accesses the database to return an array of classIDs
		@return String array of classIDs student has completed
	*/
	public String[] getClassIDs(){
		return classIDs;
	}
	
	public String getMajorID(){
		return majorID;
	}
	
	public String getMinorID(){
		return minorID;
	}
	
	/*
		getCredits - Accesses the database to return the number of credits 
		student has completed thus far. 
		
		** This needs to be calculated in the Interface class:
			- [AccessStudentData] Grab Students Completed Classes
			- [AccessCourseData]  Query each class and return the total # of credits for each
	*/
	public int getCredits(){
		return 1;
	}
}