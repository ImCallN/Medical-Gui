import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//This class will determine if the information entered on the login screen matches admin info in our "database"
//so far it is just a method
public class ValidateAdmin 
{
    public boolean runValidation(String username, String password) throws IOException
    {
       
        //Grabbing the admin info from our "database"
        File adminList = new File("C:\\Users\\CJ\\OneDrive\\Desktop\\Program Projects\\Java Projects\\Swing Projects\\GuiForData\\Gui\\src\\AdminLog.txt");
        //Buffered Reader to read the file
        BufferedReader br = new BufferedReader(new FileReader(adminList));
        //String for line by line in the file
        String streamInput;
        //ArrayList to send the data to
        ArrayList<String> fileInfo = new ArrayList<String>();
        //loop to go through the file information and store it in our arraylist
        while((streamInput = br.readLine()) != null)
        {
            fileInfo.add(streamInput);
            
        }   
        br.close();

        //loop to compare the data in the arrayList to the data that we have for username and password arguments
        for(String r : fileInfo)
        {
            //Creates an array of Strings for each entry in our arrayList
            String[] userPassSplit = r.split(",");
            //compares the first part of the string to the username
            if(username.equals(userPassSplit[0]))
            {
                System.out.println("user matches");
               if(password.equals(userPassSplit[1]))
               {
                   System.out.println("password matches");
                   return true;
               }
            }
        }
        //if they don't match, return a false
        return false;
    }
}
