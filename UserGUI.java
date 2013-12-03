import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserGUI implements ActionListener{
	// DB Accessor
	AccessCourseData DBClassAccessor = new AccessCourseData();
	AccessStudentData DBStudentAccessor;
	
	JTextArea reportTextArea;
	
	JTextField totalCreditsNeededField;
	JTextField totalCreditsTakenField;
	JTextField totalCreditsLeftField;
	
	JComboBox majorList;
	JComboBox minorList;
	
	JButton jbGenReport;
	
	String myUsername;
	String myMajor;
	String myMinor;
	
	public UserGUI(String username, String major, String minor){
		myUsername = username;
		myMajor = major;
		myMinor = minor;
		
		DBStudentAccessor = new AccessStudentData(myUsername);
		
		// Get Major from Student DB
	
		// JFrame Initialization
		JFrame myUserGUI = new JFrame("User Panel");
		myUserGUI.setLayout(new GridLayout());
		myUserGUI.setSize(720, 500);
		myUserGUI.setLocation(200, 200);
		myUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanels
		JPanel leftP = new JPanel(new GridLayout(2,0));
		JPanel rightP = new JPanel();
		JPanel leftTopP = new JPanel();
		JPanel leftBottomP = new JPanel();
		
		JPanel classIDPanel = new JPanel(new GridLayout(0,2));
		JPanel classNamePanel = new JPanel(new GridLayout(0,2));
		JPanel classCreditPanel = new JPanel(new GridLayout(0,2));
		JPanel classRequirementPanel = new JPanel(new GridLayout(0,2));
		
		// Buttons
		jbGenReport = new JButton("Generate Report");
		
		// TextArea w/ Scroll
		reportTextArea = new JTextArea(28, 30);
		reportTextArea.setEditable(false);
		JScrollPane myScrollReport = new JScrollPane(reportTextArea);
		
		// JComboBox
		String[] tempMajors = {"Select Major"};
		String[] tempMinors = {"Select Minor"};
		String[] tempClassID = {"Select Class"};
		
		majorList = new JComboBox(tempMajors);
		minorList = new JComboBox(tempMinors);
		majorList.setPreferredSize(new Dimension(320, 30));
		minorList.setPreferredSize(new Dimension(320, 30));
		
		majorList.setEnabled(false);
		minorList.setEnabled(false);
		
		// JLabels
		JLabel totalCreditsNeededLabel = new JLabel("Total Credits Needed:");
		JLabel totalCreditsTakenLabel = new JLabel("Total Credits Taken:");
		JLabel totalCreditsLeftLabel = new JLabel("Total Credits Left:");
		
		// JTextFields
		totalCreditsNeededField = new JTextField(15);
		totalCreditsTakenField = new JTextField(15);
		totalCreditsLeftField = new JTextField(15);
		
		totalCreditsNeededField.setEditable(false);
		totalCreditsTakenField.setEditable(false);
		totalCreditsLeftField.setEditable(false);
		
		// Populate before ActionListener
		populateComboBoxes();
		
		// Action Listener
		jbGenReport.addActionListener(this);
		majorList.addActionListener(this);
		minorList.addActionListener(this);
		
		// Adding the stuff

		leftTopP.add(majorList);
		leftTopP.add(minorList);		
		leftTopP.add(jbGenReport);
		
		// leftTopP.add(classList);
		//leftTopP.add(Box.createRigidArea(new Dimension(0,200)));
		classIDPanel.add(totalCreditsNeededLabel);
		classIDPanel.add(totalCreditsNeededField);
		
		classNamePanel.add(totalCreditsTakenLabel);
		classNamePanel.add(totalCreditsTakenField);
		
		classCreditPanel.add(totalCreditsLeftLabel);
		classCreditPanel.add(totalCreditsLeftField);
		
		leftBottomP.add(classIDPanel);
		leftBottomP.add(classNamePanel);
		leftBottomP.add(classCreditPanel);
		leftBottomP.add(classRequirementPanel);
		
		leftP.add(leftTopP);
		leftP.add(leftBottomP);
		rightP.add(myScrollReport);
		
		myUserGUI.add(leftP);
		myUserGUI.add(rightP);
		
		// Update fields
		int majorCreditsNeeded = DBClassAccessor.getMajorCredits(myMajor);
		int minorCreditsNeeded = DBClassAccessor.getMinorCredits(myMinor);
		
		totalCreditsNeededField.setText((majorCreditsNeeded + minorCreditsNeeded) + "");
		
		
		myUserGUI.setVisible(true);
	}
	
	public void populateComboBoxes(){
		ArrayList<String> tempMajAL;
		ArrayList<String> tempMinAL;
		
		majorList.removeAllItems();
		majorList.addItem(myMajor);
		
		minorList.removeAllItems();
		minorList.addItem(myMinor);
	}
	
	public static void main(String[] args){
		new UserGUI("exr7549", "ISTE", "NULL");
	}
	
	public void actionPerformed(ActionEvent ae){
		// Generate User Report
		if(ae.getSource() == jbGenReport){
			ArrayList<String> usersClasses = DBStudentAccessor.getClassIDs();
			ArrayList<ArrayList> usersMajorRequirements = DBClassAccessor.getMajorRequirements(myMajor);
			ArrayList<String> usersMinorRequirements = DBClassAccessor.getMinorRequirements(myMinor);
			
			ArrayList<ArrayList> classesMajStillNeeded = new ArrayList<ArrayList>();
			ArrayList<String> classesMinStillNeeded = new ArrayList<String>();
			
			for(ArrayList<String> tempAL : usersMajorRequirements){
				if(usersClasses.contains(tempAL.get(0))){
					
				}else{
					ArrayList<String> tempAL2 = new ArrayList<String>();
					tempAL2.add(tempAL.get(0));
					tempAL2.add(tempAL.get(1));
					
					classesMajStillNeeded.add(tempAL2);
				}
			}
			
			for(String tempString : classesMinStillNeeded){
				if(usersClasses.contains(tempString)){
					
				}else{
					classesMinStillNeeded.add(tempString);
				}
			}
			
			// classesMinStillNeeded (String array of Minor classes still needed)
			// classesMajStillNeeded (ArrayList array of Major classes still needed)
			
			// ---------------------------------------------------------------------------
			
			String majorReportString = "---------------------------------------------------------------------------";
				majorReportString = majorReportString + "\nMAJOR: " + majorList.getSelectedItem().toString() + "\n";
				majorReportString = majorReportString + "---------------------------------------------------------------------------";
				int count = 1;
				int previousVal = 0;
				
				for(ArrayList<String> tempAL : classesMajStillNeeded){
					try{
						if(Integer.parseInt(tempAL.get(1)) > previousVal){	
							// Output line
							majorReportString = majorReportString + "\n-----------------------------Semester " + tempAL.get(1) + " -----------------------------";
							previousVal = Integer.parseInt(tempAL.get(1));
							
							if(count % 2 == 0){
								count--;
							}
						}
					}catch(NumberFormatException nfe){
						// If no semesters, this will be thrown - ignore
					}
					
					if(count % 2 == 0){
						majorReportString = majorReportString + "\t" + tempAL.get(0) + "\t(" + tempAL.get(1) + ")";
					}else{
						majorReportString = majorReportString + "\n" + tempAL.get(0) + "\t(" + tempAL.get(1) + ")";
					}
					count++;
					
				}
				


				if(!((minorList.getSelectedItem().toString()).equals("Select Minor"))){
					majorReportString = majorReportString + "\n---------------------------------------------------------------------------";
					majorReportString = majorReportString + "\nMINOR: " + minorList.getSelectedItem().toString();
					majorReportString = majorReportString + "\n---------------------------------------------------------------------------";
					
					// Append to reportTextArea
					
					count = 1;
					
					for(String tempS : classesMinStillNeeded){
						if(count % 2 == 0){
							majorReportString = majorReportString + "\t" + tempS + "\t";
						}else{
							majorReportString = majorReportString + "\n" + tempS + "\t";
						}
						count++;
					}
				}
				
				reportTextArea.setText(majorReportString);
		}
	}
}