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
    private JButton clearButton, submitButton, firstEntryButton, lastEntryButton, nextEntryButton, previousEntryButton, searchButton;    //the buttons

    private final int SCREENRES = 500;

    //Creates the window, and if adminAccess is true, will create the window with the navigation/editing abilities
    public void createWindow(boolean adminAccess)
    {
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
            lastNameField = new JTextField(50);
            lastNameField.setVisible(true);

            //Creating FirstName textField
            firstNameField = new JTextField(50);
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
                                
                //Previous Entry Button
                previousEntryButton = new JButton("<");
                
                //Next Entry Button
                nextEntryButton = new JButton(">");
                
                //Last Entry Button
                lastEntryButton = new JButton(">|");

                //Search Button
                searchButton = new JButton("Search");
                
                //Allows editing access based on users clearance level, Admins have edits, users and guests do not.
                if(adminAccess)
                {
                    navigationButtonPanel.add(firstEntryButton);
                    navigationButtonPanel.add(previousEntryButton);
                    navigationButtonPanel.add(nextEntryButton);
                    navigationButtonPanel.add(lastEntryButton);
                    navigationButtonPanel.add(searchButton);
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

    //button operations

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == clearButton)
        {
            lastNameField.setText("");
            firstNameField.setText("");
            birthdateField.setText("");
        }
        else if(e.getSource() == submitButton)
        {
            UserEntry newUser = new UserEntry();
            newUser.populateEntry(lastNameField.getText(), firstNameField.getText(), birthdateField.getText());
            //newUser.displayUser();
            PopulateUserEntry runTheMethod = new PopulateUserEntry(newUser);
            runTheMethod.packageData();
            
        }
    }
}
