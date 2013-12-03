import java.util.*;
/*
   Samantha Oxley
   Intermediary class - created when user tries to generate data
   Acts as an intermediary between the GUI and the database classes
*/
public class Intermediary implements CommonData{
   private String username;
   private String major;
   private String minor;
   private int speed;
   private String finalOutput;
   /*
      Parameterized constructor for Intermediary Class
      @Param String username, "guest" "admin" or valid username
      @Param String major, "major" or valid major
      @Param String minor, "minor" or valid minor
      @Param Integer speed, 1 for Quickest or 2 for Slowest
   */
   public Intermediary(String username, String major, String minor, int speed){
      this.username = username;
      this.major = major;
      this.minor = minor;
      this.speed = speed;
      nextStep();
   }
   public String getNextOutput(){
      return finalOutput;
   }
   public void calculateOutput(ArrayList<String> student, ArrayList<String> all){
      
   }
   public void nextStep(){
      ArrayList<String> studentCourses = new ArrayList<String>();
      ArrayList<String> allCourses = new ArrayList<String>();
      ArrayList<String> remainingCourses = new ArrayList<String>();
      
      if(username.equalsIgnoreCase("guest")){
         AccessCourseData courses = new AccessCourseData();
         if( major != "major"){  
            //ArrayList<String> more = courses.getMajorRequirements( major );
            //for(String oneMore: more){
            //   allCourses.add( oneMore );
            //}
         }
         if( minor != "minor"){
            ArrayList<String> moreMore = courses.getMinorRequirements( minor );
            for(String oneMore: moreMore){
               allCourses.add( oneMore );
            }
         }
      }else if(username.equalsIgnoreCase("admin")){
         // get the student data from the database
         //ModifyStudentData student = new ModifyStudentData( username );
      }else{
         // get the student data from the database
         AccessStudentData student = new AccessStudentData( username );
         // get the data from the courses
      }
   }
}