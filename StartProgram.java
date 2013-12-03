import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartProgram extends JFrame implements ActionListener, CommonData{
   JButton jbSubmit;
   JTextField jtfUsername;

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
      for(int i=0; i<USERNAMES.length; i++){
         if( user.equals( USERNAMES[i] ) ) 
            return true;
      }
      return false;
   }
   
   public void actionPerformed(ActionEvent ae){
      if(ae.getActionCommand().equals("Submit")){
         String username = jtfUsername.getText();
         if( username.equalsIgnoreCase("guest") ){
            ProgUI newGuest = new ProgUI();
            // close this window
         }else if( username.equalsIgnoreCase("admin") ){
            AdminUI newAdmin = new AdminUI();
            // close this window
         }else if( isValid( username ) == true ){
            UserUI newUser = new UserUI( username );
            // close this window
         }else{
            JOptionPane.showMessageDialog(null, "Invalid username, try again.");
         }
      }
   }//end actionPerformed
}