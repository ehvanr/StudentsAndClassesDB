import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GuestGUI implements ActionListener{
	// DB Accessor
	AccessCourseData DBClassAccessor = new AccessCourseData();
	ModifyCourseData DBClassModifier = new ModifyCourseData();
	
	JTextArea reportTextArea;
	
	JComboBox majorList;
	JComboBox minorList;
	
	JButton jbGenReport;
	
	public GuestGUI(){
	
		// JFrame Initialization
		JFrame myGuestGUI = new JFrame("Guest Panel");
		myGuestGUI.setLayout(new GridLayout());
		myGuestGUI.setSize(720, 500);
		myGuestGUI.setLocation(200, 200);
		myGuestGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanels
		JPanel leftP = new JPanel(new GridLayout(2,0));
		JPanel rightP = new JPanel();
		JPanel leftTopP = new JPanel();
		
		
		// Buttons
		jbGenReport = new JButton("Generate Report");
		
		// TextArea w/ Scroll
		reportTextArea = new JTextArea(28, 30);
		reportTextArea.setEditable(false);
		JScrollPane myScrollReport = new JScrollPane(reportTextArea);
		
		// JComboBox
		String[] tempMajors = {"Select Major"};
		String[] tempMinors = {"Select Minor"};
		
		majorList = new JComboBox(tempMajors);
		minorList = new JComboBox(tempMinors);
		majorList.setPreferredSize(new Dimension(320, 30));
		minorList.setPreferredSize(new Dimension(320, 30));
		
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
		
		leftP.add(leftTopP);
		rightP.add(myScrollReport);
		
		myGuestGUI.add(leftP);
		myGuestGUI.add(rightP);
		
		
		myGuestGUI.setVisible(true);
	}
	
	public void populateComboBoxes(){
		ArrayList<String> tempMajAL;
		ArrayList<String> tempMinAL;
		
		// On load, populate Major, Minor, and Class ComboBoxes
		ArrayList<ArrayList> myMajors = DBClassAccessor.getMajors();
		ArrayList<ArrayList> myMinors = DBClassAccessor.getMinors();
		
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
		
		majorList.setSelectedItem("Select Major");
		minorList.setSelectedItem("Select Minor");
	}
	
	public static void main(String[] args){
		new GuestGUI();
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
		}
	}
}