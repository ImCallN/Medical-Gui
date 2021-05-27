import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Class to contain our GUI layout for our App

public class Gui implements ActionListener
{
    //Our GUI objects that we can visually see
    private JFrame mainWindow;                                          //the window 
    private JPanel mainPanel, botPanel, topPanel, topLabelPanel, topTextPanel, submitButtonPanel, clearButtonPanel, navigationButtonPanel;
    private JTextField firstNameField, lastNameField, birthdateField;   //the textFields
    private JLabel firstNameLabel, lastNameLabel, birthdateLabel;       //the Labels for the textFields
    private JButton clearButton, submitButton, firstEntryButton, lastEntryButton, nextEntryButton, previousEntryButton, searchButton, deleteButton;    //the buttons
    private final int SCREENRES = 500;
    private int navigationInt = 0;
    private PopulatePatientEntry populateFromFile;
    

    //Creates the window, and if adminAccess is true, will create the window with the navigation/editing abilities
    public void createWindow(boolean adminAccess)
    {
        //This creates our arrayList from the file at the beginning of the program
        populateFromFile = new PopulatePatientEntry();
        populateFromFile.createPopulatedArrayList();        //This method will populate our arrayList from the file
        
        //Toolkit to grab Screen Dimensions
        Toolkit screenToolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimensions = screenToolkit.getScreenSize();

        //vars to hold the location of the window based on the screen size minus the window size
        int windowHeight = (int) (screenDimensions.getHeight() - SCREENRES)/2;
        int windowWidth = (int) (screenDimensions.getWidth() - SCREENRES)/2;

        //creating the frame object
        mainWindow = new JFrame("User Input Screen");
        mainWindow.setSize(SCREENRES, SCREENRES);
        mainWindow.setLocation(windowWidth, windowHeight);              //Sets the location of the window based on screen size
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel things
        mainPanel = new JPanel(new GridLayout(2,0));
        topPanel = new JPanel(new GridLayout(8,0));
        topLabelPanel = new JPanel(new GridLayout(0,3));
        topTextPanel = new JPanel(new GridLayout(0,3));
        botPanel = new JPanel(new GridLayout(0,6));
        submitButtonPanel = new JPanel(new GridLayout(6,0));
        clearButtonPanel = new JPanel(new GridLayout(6,0));
        navigationButtonPanel = new JPanel(new GridLayout(5,0));

        //Creating the Labels
            //lastName Label
            lastNameLabel = new JLabel("Last Name");
       
            //firstName Label
            firstNameLabel = new JLabel("First Name");
  
            //Birthday label
            birthdateLabel = new JLabel("DOB in mmddyyyy");

            //adding labels
            topLabelPanel.add(lastNameLabel);
            topLabelPanel.add(firstNameLabel);
            topLabelPanel.add(birthdateLabel);
        
        //Creating the textFields

            //Creating lastName textField
            lastNameField = new JTextField();
            lastNameField.setVisible(true);
           

            //Creating FirstName textField
            firstNameField = new JTextField();
            firstNameField.setVisible(true);


            //Creating birthdate textField
            birthdateField = new JTextField(8);
            birthdateField.setVisible(true);
            
            //Adds a keyListener to make sure we can only put numbers in the birthdate field
            birthdateField.addKeyListener(new KeyAdapter() 
            {
                public void keyPressed(KeyEvent ke)
                {
                    if((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')||(ke.getKeyChar() == 8))
                    {
                        birthdateField.setEditable(true);
                    }
                    else
                    {
                        birthdateField.setEditable(false);
                    }
                }
            });
            
                
            //adding textfields to panels
            topTextPanel.add(lastNameField);
            topTextPanel.add(firstNameField);
            topTextPanel.add(birthdateField);
        
        //Creating buttons

            //Navigation Buttons

                //First Entry Button
                firstEntryButton = new JButton("|<");
                firstEntryButton.addActionListener(this);

                //Previous Entry Button
                previousEntryButton = new JButton("<");
                previousEntryButton.addActionListener(this);

                //Next Entry Button
                nextEntryButton = new JButton(">");
                nextEntryButton.addActionListener(this);

                //Last Entry Button
                lastEntryButton = new JButton(">|");
                lastEntryButton.addActionListener(this);

                //Search Button
                searchButton = new JButton("Search");
                searchButton.addActionListener(this);

                //Delete Button
                deleteButton = new JButton("Delete");
                deleteButton.addActionListener(this);
                
                //Allows editing access based on users clearance level, Admins have edits, users and guests do not.
                if(adminAccess)
                {
                    navigationButtonPanel.add(firstEntryButton);
                    navigationButtonPanel.add(previousEntryButton);
                    navigationButtonPanel.add(nextEntryButton);
                    navigationButtonPanel.add(lastEntryButton);
                    navigationButtonPanel.add(searchButton);
                    clearButtonPanel.add(deleteButton);
                }

            //Submit Button
            submitButton = new JButton("Submit");
            submitButton.addActionListener(this);
            
            //Clear Button
            clearButton = new JButton("Clear");
            clearButton.addActionListener(this);
  
            submitButtonPanel.add(submitButton);
            clearButtonPanel.add(clearButton);

        //add the panel to the frame
        topPanel.add(topLabelPanel);
        topPanel.add(topTextPanel);
        botPanel.add(navigationButtonPanel);
        botPanel.add(new JLabel(""));
        botPanel.add(submitButtonPanel);
        botPanel.add(clearButtonPanel);
        mainPanel.add(topPanel);
        mainPanel.add(botPanel);
        mainWindow.add(mainPanel);
        mainWindow.setVisible(true);
    }

    //Function to link the arrayList from our PopulatePatientEntry object to the gui so that we can populate
    //the textfields with the information from our "database"
    public void displayInfoInFields(PatientEntry currentPatient)
    {
        lastNameField.setText(currentPatient.lastName);
        firstNameField.setText(currentPatient.firstName);
        birthdateField.setText(currentPatient.birthdate);
    }

    //button operations
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //This is a proof of concept way that I think I'm going to do right now, I will improve upon this later
        if(e.getSource() == clearButton)
        {
            lastNameField.setText("");
            firstNameField.setText("");
            birthdateField.setText("");
        }
        if(e.getSource() == submitButton)
        {
            //Creates a new patientEntry obj
            PatientEntry inputPatient = new PatientEntry();
            //populates the patientEntry obj off of the textFields
            inputPatient.populateFromFields(lastNameField.getText(), firstNameField.getText(), birthdateField.getText());

            populateFromFile.joinAddandCheck(inputPatient);
            
            //This is just for test to make sure it will write to the file
            populateFromFile.writePatientsToFile();
        }

        //Search Button Actions
        if(e.getSource() == searchButton)   
        {
            
        }
        //First Entry Button Action
        if(e.getSource() == firstEntryButton)
        {
            navigationInt = 0;
            displayInfoInFields(populateFromFile.navigateList(navigationInt));
        }
        //Last Entry Button Action
        if(e.getSource() == lastEntryButton)
        {
            navigationInt = populateFromFile.listSize() - 1;
            displayInfoInFields(populateFromFile.navigateList(navigationInt));
           
        }
        //Next Entry Button Action
        if(e.getSource() == nextEntryButton)
        {
            if(navigationInt < populateFromFile.listSize() - 1)
            {
                navigationInt++;
            }
            displayInfoInFields(populateFromFile.navigateList(navigationInt));
            
        }
        //Previous Entry Button Action
        if(e.getSource() == previousEntryButton)
        {
            if(navigationInt > 0)
            {
                navigationInt--;
            }
            displayInfoInFields(populateFromFile.navigateList(navigationInt));
            
        }
    }
}
