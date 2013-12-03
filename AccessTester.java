import java.util.ArrayList;

public class AccessTester{
	public static void main(String [] args){
		AccessCourseData testerCData = new AccessCourseData();
		AccessStudentData testerSData = new AccessStudentData("user");
		
		// ---------------------------------------
		// ---------------------- AccessCourseData
		// ---------------------------------------
		
		// Tests getMinorRequirements
		System.out.println("\ngetMinorRequirements:");
		ArrayList<String> tempAL = testerCData.getMinorRequirements("NSSA");
		
		for(String tempS : tempAL){
			System.out.println(tempS);
		}

		// Tests getClassRequirements
		System.out.println("\n\ngetClassRequirements:");

		ArrayList<String> tempAL2 = testerCData.getClassRequirements("CSCI-320");
		for(String tempS : tempAL2){
			System.out.println(tempS);
		}
		
		// Tests getMajorRequirements
		System.out.println("\n\ngetMajorRequirements:");
		System.out.println("ClassID\t\tSemester");

		ArrayList<ArrayList> tempAL3 = testerCData.getMajorRequirements("ISTE");
		for(ArrayList tempSubAL : tempAL3){
			// This is a fixed size, tempSubAL will be of size 2 (0-1)
			
			//ClassID, Semester
			System.out.println(tempSubAL.get(0) + "\t" + tempSubAL.get(1));
		}
		
		// Tests getMajors
		System.out.println("\n\ngetMajors:");
		System.out.printf("\n%-7s  %-36s  %-14s\n", "MajorID", "MajorName", "Credits Needed");

		ArrayList<ArrayList> tempAL4 = testerCData.getMajors();
		for(ArrayList tempSubAL : tempAL4){
			// This is a fixed size, tempSubAL will be of size 3 (0-2)
			
			// MajorID, MajorName, Credits Needed
			System.out.printf("\n%-7s  %-36s  %-14s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2));
		}
		
		// Tests getMinors
		System.out.println("\n\ngetMinors:");
		System.out.printf("\n%-7s  %-50s  %-14s\n", "MinorID", "MinorName", "Credits Needed");

		ArrayList<ArrayList> tempAL5 = testerCData.getMinors();
		for(ArrayList tempSubAL : tempAL5){
			// This is a fixed size, tempSubAL will be of size 3 (0-2)
			
			// MinorID, MinorName, Credits Needed
			System.out.printf("\n%-8s  %-50s  %-14s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2));
		}
		
		// Tests getClasses
		System.out.println("\n\ngetClasses:");
		System.out.printf("\n%-8s  %-60s  %-14s  %-20s\n", "ClassID", "ClassName", "Credits Value", "Requirements");

		ArrayList<ArrayList> tempAL6 = testerCData.getClasses();
		for(ArrayList tempSubAL : tempAL6){
			// This is a fixed size, tempSubAL will be of size 4 (0-3)
			
			// ClassID, ClassName, Credit Value, Requirements
			System.out.printf("\n%-7s  %-60s  %-14s  %-20s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2), tempSubAL.get(3));
		}
		
		// Tests getClassCredit
		System.out.println("\n\ngetClassCredit");
		System.out.println(testerCData.getClassCredit("BIOL-101"));
		
		// Tests getClassTotalCredits
		System.out.println("\n\ngetClassTotalCredits");
		System.out.println(testerCData.getClassTotalCredits(tempAL));
		
		// ---------------------------------------
		// -------------------- ModifyCourseData
		// ---------------------------------------
		
		// Remove Class
		ModifyCourseData tempModify = new ModifyCourseData();
		tempModify.removeClass("ISTE-371");
		
		// Add Class
		tempModify.addClass("ISTE-331", "Integration in Infomativs", "3", "ISTE-230");
		
		// Update Class
		tempModify.updateClassesTable("ISTE-371", "Integration In Informatics", "3", "");
		
		// ---------------------------------------
		// --------------------  ModifyStudentData
		// ---------------------------------------
		
		// Remove student
		ModifyStudentData tempStudModify = new ModifyStudentData();
		tempStudModify.deleteStudent("exr7549");
		
		// Add student
		tempStudModify.addStudent("exr7549","Evan Reichard","ISTE-120, ISTE-190, ISTE-121, ISTE-140, ISTE-110, MATH-131, MATH-161, ISTE-230, ISTE-260, ISTE-240, STAT-145, STAT-146, ISTE-330, ISTE-430","ISTE", "");
		
		// Update student
		tempStudModify.updateStudent("exr7549", "exr7549","Evan Reichard","ISTE-120, ISTE-190, ISTE-121, ISTE-140, ISTE-110, MATH-131, MATH-161, ISTE-230, ISTE-260, ISTE-240, STAT-145, STAT-146, ISTE-330, ISTE-430","ISTE", "NULL");
	}
}