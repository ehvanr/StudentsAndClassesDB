import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminGUI implements ActionListener{
	// DB Accessor
	AccessCourseData DBClassAccessor = new AccessCourseData();
	ModifyCourseData DBClassModifier = new ModifyCourseData();
	
	JTextArea reportTextArea;
	
	JTextField classIDField;
	JTextField classNameField;
	JTextField classCreditField;
	JTextField classRequirementsField;
	
	JComboBox majorList;
	JComboBox minorList;
	JComboBox classList;
	
	JButton jbGenReport;
	JButton jbUpdateAddClass;
	JButton jbDeleteClass;
	
	public AdminGUI(){
		// JFrame Initialization
		JFrame myAdminGUI = new JFrame("Admin Panel");
		myAdminGUI.setLayout(new GridLayout());
		myAdminGUI.setSize(720, 500);
		myAdminGUI.setLocation(200, 200);
		myAdminGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanels
		JPanel leftP = new JPanel(new GridLayout(2,0));
		JPanel rightP = new JPanel();
		JPanel leftTopP = new JPanel();
		JPanel leftBottomP = new JPanel();
		
		JPanel classIDPanel = new JPanel(new GridLayout(0,2));
		JPanel classNamePanel = new JPanel(new GridLayout(0,2));
		JPanel classCreditPanel = new JPanel(new GridLayout(0,2));
		JPanel classRequirementPanel = new JPanel(new GridLayout(0,2));
		JPanel classButtonPanel = new JPanel();
		
		// Buttons
		jbGenReport = new JButton("Generate Report");
		jbUpdateAddClass = new JButton("Update / Add Class");
		jbDeleteClass = new JButton("Delete Class");
		
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
		classList = new JComboBox(tempClassID);
		classList.setPreferredSize(new Dimension(320, 30));
		majorList.setPreferredSize(new Dimension(320, 30));
		minorList.setPreferredSize(new Dimension(320, 30));
		
		// JLabels
		JLabel classIDLabel = new JLabel("ID:");
		JLabel classNameLabel = new JLabel("Name:");
		JLabel classCreditLabel = new JLabel("Credit Value:");
		JLabel classRequirementsLabel = new JLabel("Requirements:");
		
		// JTextFields
		classIDField = new JTextField(15);
		classNameField = new JTextField(15);
		classCreditField = new JTextField(15);
		classRequirementsField = new JTextField(15);
		
		// Populate before ActionListener
		populateComboBoxes();
		
		// Action Listener
		jbGenReport.addActionListener(this);
		jbUpdateAddClass.addActionListener(this);
		jbDeleteClass.addActionListener(this);
		majorList.addActionListener(this);
		minorList.addActionListener(this);
		classList.addActionListener(this);
		
		// Adding the stuff

		leftTopP.add(majorList);
		leftTopP.add(minorList);		
		leftTopP.add(jbGenReport);
		
		leftTopP.add(classList);
		leftTopP.add(Box.createRigidArea(new Dimension(0,200)));
		classIDPanel.add(classIDLabel);
		classIDPanel.add(classIDField);
		
		classNamePanel.add(classNameLabel);
		classNamePanel.add(classNameField);
		
		classCreditPanel.add(classCreditLabel);
		classCreditPanel.add(classCreditField);
		
		classRequirementPanel.add(classRequirementsLabel);
		classRequirementPanel.add(classRequirementsField);
		
		classButtonPanel.add(jbUpdateAddClass);
		classButtonPanel.add(jbDeleteClass);
		
		leftBottomP.add(classIDPanel);
		leftBottomP.add(classNamePanel);
		leftBottomP.add(classCreditPanel);
		leftBottomP.add(classRequirementPanel);
		leftBottomP.add(classButtonPanel);
		
		leftP.add(leftTopP);
		leftP.add(leftBottomP);
		rightP.add(myScrollReport);
		
		myAdminGUI.add(leftP);
		myAdminGUI.add(rightP);
		
		myAdminGUI.setVisible(true);
	}
	
	public void populateComboBoxes(){
		ArrayList<String> tempMajAL;
		ArrayList<String> tempMinAL;
		ArrayList<String> tempClassesAL;
		
		classIDField.setText("");
		classNameField.setText("");
		classCreditField.setText("");
		classRequirementsField.setText("");
		
		// On load, populate Major, Minor, and Class ComboBoxes
		ArrayList<ArrayList> myMajors = DBClassAccessor.getMajors();
		ArrayList<ArrayList> myMinors = DBClassAccessor.getMinors();
		ArrayList<ArrayList> myClasses = DBClassAccessor.getClasses();
		
		majorList.removeAllItems();
		majorList.addItem("Select Major");
		// Majors
		for(ArrayList<String> tempAL : myMajors){
			majorList.addItem(tempAL.get(0) + " (" + tempAL.get(1) + ")");
		}
		
		minorList.removeAllItems();
		minorList.addItem("Select Minor");
		// Minors
		for(ArrayList<String> tempAL : myMinors){
			minorList.addItem(tempAL.get(0) + " (" + tempAL.get(1) + ")");
		}
		
		classList.removeAllItems();
		classList.addItem("Select Class");
		// Classes
		for(ArrayList<String> tempAL : myClasses){
			classList.addItem(tempAL.get(0) + " (" + tempAL.get(1) + ")");
		}
		
		majorList.setSelectedItem("Select Major");
		minorList.setSelectedItem("Select Minor");
		classList.setSelectedItem("Select Class");
	}
	
	public static void main(String[] args){
		new AdminGUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource() == jbGenReport){
			
			String majValue = (majorList.getSelectedItem().toString()).substring(0,4);
			String minValue = (minorList.getSelectedItem().toString()).substring(0,4);
			
			if((majorList.getSelectedItem().toString()).equals("Select Major")){
				// JOptionPane Alert "You must select at least one major"
			}else{
				// Clear TextArea
				reportTextArea.setText("");
				
				// Query DB, output to TextArea
				ArrayList<ArrayList> tempMajReqAL = DBClassAccessor.getMajorRequirements(majValue);
				
				ArrayList<ArrayList> newSemesterBreakdown = new ArrayList<ArrayList>();
				
				String majorReportString = "---------------------------------------------------------------------------";
				majorReportString = majorReportString + "\nMAJOR: " + majorList.getSelectedItem().toString() + "\n";
				majorReportString = majorReportString + "---------------------------------------------------------------------------";
				int count = 1;
				int previousVal = 0;
				
				for(ArrayList<String> tempAL : tempMajReqAL){
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
					
					ArrayList<String> tempMinReqAL = DBClassAccessor.getMinorRequirements(minValue);
					// Append to reportTextArea
					
					count = 1;
					
					for(String tempS : tempMinReqAL){
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
			
		}else if(ae.getSource() == jbUpdateAddClass){
			boolean toContinue = true;
			
			// Get value of classIDField
			String tempVal = classIDField.getText();
			
			// Inputs all ClassID's into tempClassIDs
			ArrayList<ArrayList> tempClasses = DBClassAccessor.getClasses();
			ArrayList<String> tempClassIDs = new ArrayList<String>();
			
			for(ArrayList<String> tempAL : tempClasses){
				tempClassIDs.add(tempAL.get(0));
			}
			
			// Inputs all in-putted Requirement values into classReqAL
			String tempClassReq = classRequirementsField.getText();
			String [] tempClassReqArray = tempClassReq.split(",");
			ArrayList<String> classReqAL = new ArrayList<String>();
			
			for(String tempS : tempClassReqArray){
				tempS = tempS.trim();
				classReqAL.add(tempS);
			}
			
			// Compares inuttes requirements
			for(String tempS : classReqAL){
				if(!(tempClassIDs.contains(tempS)) && !(tempS.equals(""))){
					// JOptionPane alert that tempS is not in the DB
					System.out.println(tempS + " is not in the DB");
					toContinue = false;
				}
			}
			
			// Should we continue?
			if(toContinue){
				String tempClassIDValue = classIDField.getText();
				String tempClassNameValue = classNameField.getText();
				String tempClassCreditValue = classCreditField.getText();
				String tempClassRequirementValue = classRequirementsField.getText();
				
				// Determines if we're updating or adding
				if(tempClassIDs.contains(tempVal)){
					// Updating
					// String classID, String className, String creditValue, String requirements
					DBClassModifier.updateClassesTable(tempClassIDValue, tempClassNameValue, tempClassCreditValue, tempClassRequirementValue);
				}else{
					// Adding
					DBClassModifier.addClass(tempClassIDValue, tempClassNameValue, tempClassCreditValue, tempClassRequirementValue);
				}
				
				populateComboBoxes();
			}else{
				toContinue = true;
			}

		}else if(ae.getSource() == jbDeleteClass){
			// Get value of classIDField
			String tempVal = classIDField.getText();
			
			// Execute Delete DB Value
			DBClassModifier.removeClass(tempVal);
			
			// Repopulate ComboBoxes
			populateComboBoxes();
			
			// Clear TextFields
			classIDField.setText("");
			classNameField.setText("");
			classCreditField.setText("");
			classRequirementsField.setText("");
			
		}else if(ae.getSource() == classList){
			// Get value of classList
			// Query DB, input appropriate values into JTextFields
			try{
				String tempClassID = classList.getSelectedItem().toString();
				
				if(!tempClassID.equals("Select Class")){
					tempClassID = tempClassID.substring(0,8);
					
					ArrayList<String> tempAL = DBClassAccessor.getClass(tempClassID);
					
					//<b>Columns:</b> 'ClassID', 'ClassName', 'CreditValue', 'Requirements'
					
					classIDField.setText(tempAL.get(0));
					classNameField.setText(tempAL.get(1));
					classCreditField.setText(tempAL.get(2));
					classRequirementsField.setText(tempAL.get(3));
					
				}
			}catch(Exception e){
				
			}
			
		}
	}
}