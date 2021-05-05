import javax.swing.SwingUtilities;
public class App 
{ 
    private LoginGui gui;
    public static void main(String[] args) throws Exception 
    {        
        App app = new App();
        app.gui = new LoginGui();
        
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                app.gui.displayPromptWindow();
                
            }
        });    
    }
}
