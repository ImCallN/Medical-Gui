import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class PopulateUserEntry
{
    

    //arraylist
    ArrayList<UserEntry> patientList = new ArrayList<UserEntry>();

    

    //output string
    String csvString;

    //File path
    String filePath = "C:\\Users\\CJ\\OneDrive\\Desktop\\Program Projects\\Java Projects\\Swing Projects\\GuiForData\\Gui\\src\\data.csv";
    



    //constructor to create the populateUserEntry Object
    PopulateUserEntry(UserEntry user)
    {
        csvString = user.lastName + "," + user.firstName + "," + user.birthdate;   
    }

    //blank constructor
    PopulateUserEntry()
    {

    }

    //method to package the data in a csv format and append it to our file
    public boolean checkForDuplicate()
    {
        File dataFile = new File(filePath);
        try
        {
            //scanner to go through the stuff
            //Don't know where to close the scanner, the one place that it would work was causing issues.
            Scanner scannyBoi = new Scanner(dataFile);

            //loop through the lines in the file
            while(scannyBoi.hasNextLine())
            {
                String comparableString = scannyBoi.nextLine();
                if(comparableString.equals(csvString))
                {
                    return false;
                }
                //tried closing the scanner here, works great on the first entry, doesn't work for a second entry
                //scannyBoi.close
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    //sends our data to the file
    public void packageData()
    {
        //if this comes back true write to file
        if(checkForDuplicate())
        {
            File file = new File(filePath);

            try {
                appendToFileWriter(file, csvString);
            } catch (IOException e) {
                //  Auto-generated catch block
                e.printStackTrace();
            }
        }
        else System.out.println("Patient Info already exists");
    }


    //this method will append the text onto the file and won't string it along in one giant string, and only append to the file if the user type is a guest
    private void appendToFileWriter(File file, String content) 
    throws IOException
    {
            try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw))
            {
                bw.write(csvString);
                bw.newLine();
            }
    }
}
    

