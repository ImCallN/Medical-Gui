import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class PopulatePatientEntry
{  
    //ArrayList to hold all of our patient data from our file
    private ArrayList<PatientEntry> patientList = new ArrayList<PatientEntry>();

    //filePath to hold where our file is

            //Can be changed whenever, this is just where mine is, but this is probably where we would link it with a database or something similar and we would
            //throw in some sql queries and the like.
    String filePath = "C:\\Users\\CJ\\OneDrive\\Desktop\\Program Projects\\Java Projects\\Swing Projects\\GuiForData\\Gui\\src\\data.csv";

    //Empty Constructor
    PopulatePatientEntry()
    {

    }

    //Method to populate the arraylist from file
    public void createPopulatedArrayList()
    {
        //tries a scanner off of a file from filepath
        try (Scanner s = new Scanner(new File(filePath)))
        {
            //while loop to loop through every line in the file
            while(s.hasNextLine())
            {
                //new PatientEntry Obj
                PatientEntry tempPatient = new PatientEntry();

                //Sets the .csvFormat field to the line
                tempPatient.csvFormat = s.nextLine();

                //runs the populateFromCSV method to populate fields off of the csv string
                tempPatient.populateFromCSV();

                //Adds PatientEntry obj to the arrayList
                patientList.add(tempPatient);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot find database");
        }
    }

    //Method to compare a PatientEntry Object against the patientList arrayList, returns true for a duplicate being found and false for no duplicates
    public boolean checkForPatientDuplicates(PatientEntry targetPatient)
    {
        for(PatientEntry a : patientList)
        {
            //Runs the class method compareTo to check for duplicates
            if(targetPatient.compareTo(a))
            {
                return true;
            }
        }
        return false;
    }

    //adds to list
    public void addPatientToList(PatientEntry patientToAdd)
    {
        patientList.add(patientToAdd);
    }

    //Method to join the add method and the check method
    public void joinAddandCheck(PatientEntry patient)
    {
        //if false
        if(!checkForPatientDuplicates(patient))
        {
            //add
            addPatientToList(patient);
            System.out.println("Patient Added");
        }
        //Let us know that there is already a patient with that information in the system.
        else System.out.println("User already exists!");
    }

    //Method to write the arrayList to the file and overwrite the data on the file with the patientList
    public void writePatientsToFile()
    {
        try
        {
            FileWriter myWriter = new FileWriter(filePath);
            //For all elements in the arraylist, write the current element's csvFormat string to the file, and then newline
            for(PatientEntry a : patientList)
            {
                myWriter.write(a.csvFormat);
                myWriter.write('\n');
            }
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error in writing to file");
            e.printStackTrace();
        }
    }

    //method to display arrayList info in the terminal for us
    public void displayArrayList()
    {
        System.out.println("==========================");

        System.out.println("|   Array Information    |  ");
        System.out.println("==========================");
        System.out.println("Array Length: " + patientList.size());
        for(PatientEntry a : patientList)
        {
            System.out.println(a.csvFormat);
        }
        System.out.println("End of Display");
        System.out.println("----------------------------");
    }

    //method for the gui to interact with the arraylist and navigate through it
    public PatientEntry navigateList(int position)
    {
        return patientList.get(position);
    }

    //method to return the size of the array to the gui
    public int listSize()
    {
        return patientList.size();
    }

    //method to search through the arraylist by firstname, returns a -1 if target not found and the indexPosition if it is found
    public int searchList(String searchTarget)
    {
        for(PatientEntry r : patientList)
        {
            if(r.lastName.equals(searchTarget))
            {
                return patientList.indexOf(r);
            }
        }
        return -1;
    }


}
    

