import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GuestGUI implements ActionListener{
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
	
	JButton jbGenReport;
	JButton jbUpdateAddClass;
	JButton jbDeleteClass;
	
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
		majorList.setPreferredSize(new Dimension(320, 30));
		minorList.setPreferredSize(new Dimension(320, 30));
		
		majorList.setEnabled(false);
		minorList.setEnabled(false);
		
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
		
		// Adding the stuff

		leftTopP.add(majorList);
		leftTopP.add(minorList);		
		leftTopP.add(jbGenReport);
		
		// leftTopP.add(classList);
		//leftTopP.add(Box.createRigidArea(new Dimension(0,200)));
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
		
		myGuestGUI.add(leftP);
		myGuestGUI.add(rightP);
		
		
		myGuestGUI.setVisible(true);
	}
	
	public void populateComboBoxes(){
		ArrayList<String> tempMajAL;
		ArrayList<String> tempMinAL;
		
		classIDField.setText("");
		classNameField.setText("");
		classCreditField.setText("");
		classRequirementsField.setText("");
		
		majorList.removeAllItems();
		majorList.addItem("");
		
		minorList.removeAllItems();
		minorList.addItem("Select Minor");
	}
	
	public static void main(String[] args){
		new GuestGUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		
	}
}