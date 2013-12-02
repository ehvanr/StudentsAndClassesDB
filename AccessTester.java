import java.util.ArrayList;

public class AccessTester{
	public static void main(String [] args){
		AccessCourseData testerCData = new AccessCourseData();
		AccessStudentData testerSData = new AccessStudentData("user");
		
		ArrayList<String> tempAL = testerCData.getMinorRequirements("NSSA");
		
		for(String tempS : tempAL){
			System.out.println(tempS);
		}
	}
}