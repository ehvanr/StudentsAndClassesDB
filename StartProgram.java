import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartProgram extends JFrame implements ActionListener{
   JButton jbSubmit;
   JTextField jtfUsername;
   String major;
   String minor;

   public static void main( String [] args ){
      StartProgram newProgram = new StartProgram();
   }
   
   public StartProgram(){
      setVisible(true);
      setLocation(300, 400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      initComponents();
      pack();
   }
   
   public void initComponents(){
      JPanel jpCenter = new JPanel();
      JPanel jpSouth = new JPanel();
      JLabel jlInfo = new JLabel("Welcome to Scheduler! \n Please enter your RIT username or \"guest\"!");
      jtfUsername = new JTextField(15);
      jbSubmit = new JButton("Submit");
      jpCenter.add(jlInfo);
      jpSouth.add(jtfUsername);
      jpSouth.add(jbSubmit);
      
      add(jpCenter, BorderLayout.CENTER);
      add(jpSouth, BorderLayout.SOUTH);
      
      jbSubmit.addActionListener( this );
    }
	
	public boolean isValid( String user ){
		// Pull User from DB
		AccessStudentData tempStudentAccessor = new AccessStudentData();
	  
		if(tempStudentAccessor.configureUser(user)){
			major = tempStudentAccessor.getMajorID();
			minor = tempStudentAccessor.getMinorID();
			return true;
		}else{
			return false;
		}
	}
   
   public void actionPerformed(ActionEvent ae){
      if(ae.getActionCommand().equals("Submit")){
	  
         String username = jtfUsername.getText();
		 
         if( username.equalsIgnoreCase("guest") ){
            GuestGUI newGuest = new GuestGUI();
            // close this window
         }else if( username.equalsIgnoreCase("admin") ){
            AdminGUI newAdmin = new AdminGUI();
            // close this window
         }else if( isValid( username ) == true ){
            UserGUI newUser = new UserGUI(username, major, minor);
            // close this window
         }else{
            JOptionPane.showMessageDialog(null, "Invalid username, try again.");
         }
      }
   }//end actionPerformed
}