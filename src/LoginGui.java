import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Gui that will run and prompt the user to either login or continue as a guest
public class LoginGui implements ActionListener 
{
    private static JFrame promptWindow;
    private JPanel mainPanel, promptPanel, promptTopPanel, promptMidPanel, promptBotPanel
    , loginPanel, loginUserNameFieldPanel, loginPasswordFieldPanel, loginButtonPanel, loginEmptyPanel;
    private JLabel prompt, loginErrorLabel;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JButton guestButton, loginButton, loginScreenLoginButton, loginScreenBackButton;
    private CardLayout cards;
    

    //var to track what screen we are in
    private boolean promptScreen = true;

    private final int PROMPTSCREENREZ = 500;

    //Empty Constructor
    LoginGui()
    {

    }

    //Method to open a gui based on a boolean but it wasn't working, come back to this later
    private static void goToNextGui(boolean access)
    {
        if(access)
        {
            System.out.println("This part went through");
            Gui mainGui = new Gui();
            mainGui.createWindow(access);
            promptWindow.dispose();
        }
    }
    

    //Method to display the prompt/login screen
    public void displayPromptWindow()
    {
        //Toolkit creation to get the screen resolution and size
        Toolkit promptScreenToolkit = Toolkit.getDefaultToolkit();
        Dimension promptScreenDimension = promptScreenToolkit.getScreenSize();

        //Screen 
        int promptWindowHeight = (int) (promptScreenDimension.getHeight() - PROMPTSCREENREZ)/2;
        int promptWindowWidth = (int) (promptScreenDimension.getWidth() - PROMPTSCREENREZ)/2;

        //JFrame
        //------------------------------------------------------------------
        //JFrame Properties
        promptWindow = new JFrame("Login Window");
        promptWindow.setSize(PROMPTSCREENREZ, PROMPTSCREENREZ/2);
        promptWindow.setLocation(promptWindowWidth, promptWindowHeight);
        promptWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //-------------------------------------------------------------------

        
        //Panel Creations
        //---------------------------------------------------------------------
            
            //card layout creation
            cards = new CardLayout();

            //Main Panel Creation implements cards CardLayout
            mainPanel = new JPanel(cards);

            //Prompt Panel Creation
            promptPanel = new JPanel(new GridLayout(4,0));
            promptTopPanel = new JPanel(new GridLayout(0,3));
            promptMidPanel = new JPanel(new GridLayout(0,3));
            promptBotPanel = new JPanel(new GridLayout(0,3));
       
        
            //Label Creation
            //------------------------------------------------------------------
            //Prompt Label 
            prompt = new JLabel("Login or continue as Guest");     
            //adds label to the panel
            promptTopPanel.add(new JLabel(""));
            promptTopPanel.add(prompt);
            //-------------------------------------------------------------------




            //Button Creation
            //-------------------------------------------------------------------
            loginButton = new JButton("Login");
                loginButton.addActionListener(this);
                
                promptMidPanel.add(new JLabel(""));
                //add button to panel
                promptMidPanel.add(loginButton);

            guestButton = new JButton("Guest");
                guestButton.addActionListener(this);
                //add blank label for white space
               
                promptBotPanel.add(new JLabel(""));
                //add button to panel
                promptBotPanel.add(guestButton);
            //-------------------------------------------------------------------

            //Login Panel Creation
            loginPanel = new JPanel(new GridLayout(8,0));
            loginUserNameFieldPanel = new JPanel(new GridLayout(0,3));
            loginPasswordFieldPanel = new JPanel(new GridLayout(0,3));
            loginEmptyPanel = new JPanel();
            loginButtonPanel = new JPanel(new GridLayout(0,4));

            //login screen labels
            loginErrorLabel = new JLabel("");


            //login screen text fields
                //the username textField and its subsequent panel
                userNameTextField = new JTextField(20);
                loginUserNameFieldPanel.add(new JLabel("UserName:"));
                loginUserNameFieldPanel.add(userNameTextField);
                
                //the password textField and its subsequent panel
                passwordField = new JPasswordField(20);
                loginPasswordFieldPanel.add(new JLabel("Password:"));
                loginPasswordFieldPanel.add(passwordField);


            //login screen buttons
            
            loginScreenLoginButton = new JButton("Login");
            loginScreenLoginButton.addActionListener(this);
            loginScreenBackButton = new JButton("Back");
            loginScreenBackButton.addActionListener(this);

            loginButtonPanel.add(new JLabel(""));
            loginButtonPanel.add(loginScreenLoginButton);
            loginButtonPanel.add(loginScreenBackButton);

            //Adding the panels
            loginPanel.add(loginErrorLabel);
            loginPanel.add(loginUserNameFieldPanel);
            loginPanel.add(loginPasswordFieldPanel);
            loginPanel.add(loginEmptyPanel);
            loginPanel.add(loginButtonPanel);
            
        //-----------------------------------------------------------------------




        //Adding the panels to their parent panel
        promptPanel.add(promptTopPanel);
        promptPanel.add(promptMidPanel);
        promptPanel.add(promptBotPanel);

        mainPanel.add(promptPanel, "mainPanelPrompt");
        mainPanel.add(loginPanel, "mainPanelLogin");



        //Adding mainPanel to the loginWindow
        promptWindow.add(mainPanel);
        
        //loginWindow settings
        promptWindow.setVisible(true);

        
    }

    //Button actionEvent
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(promptScreen)
        {
            if(e.getSource() == loginButton)
            {
                
                promptScreen = false;
                cards.show(mainPanel, "mainPanelLogin");
            }
            else if(e.getSource() == guestButton)
            {
                
                //open Gui without admin permissions
                
                Gui mainGui = new Gui();
                mainGui.createWindow(false);
                promptWindow.dispose();
                

                //goToNextGui(false);
            }
        }
        else
        {
            if(e.getSource() == loginScreenLoginButton)
            {
                //Creates our validateAdminObject
                ValidateAdmin validationObject = new ValidateAdmin();
                boolean accessGranted = false;
                //Runs the method in the class to test the info and sets our boolean to the value returned
                try 
                    {
                        accessGranted = validationObject.runValidation(userNameTextField.getText(), (new String(passwordField.getPassword())));
                    }   
                    catch (IOException e1) 
                    {
                        e1.printStackTrace();
                    }
                    
                //if runValidation returns true, open Gui with admin permissions
                if(accessGranted)
                {
                    Gui mainGui = new Gui();
                    mainGui.createWindow(accessGranted);
                    promptWindow.dispose();

                    //goToNextGui(accessGranted);
                }
                else
                {
                    //Display error for the wrong username and password
                    loginErrorLabel.setText("Wrong Username or Password!");
                    userNameTextField.setText("");
                    passwordField.setText("");
                }

            }
            else if(e.getSource() == loginScreenBackButton)
            {
                //sends you back to the prompt panel
                cards.show(mainPanel, "mainPanelPrompt");
                loginErrorLabel.setText("");
                //tells us that we're back in the prompt screen
                promptScreen = true;
            }
        }
    } 
   
}       
