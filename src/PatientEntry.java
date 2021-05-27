public class PatientEntry 
{
    String firstName;
    String lastName;
    String birthdate; 
    String csvFormat;
    
    public void populateFromFields(String last, String first, String birth)
    {
        lastName = last;
        firstName = first;
        birthdate = birth;
        csvFormat = lastName + "," + firstName + "," + birthdate;
    }

    public void displayPatient()
    {
        System.out.println(csvFormat);
    }

    //method to compare current object to another object and compare their csvFormat strings against each other
    public boolean compareTo(PatientEntry targetEntry)
    {
        if(csvFormat.equals(targetEntry.csvFormat))
        {
            return true;
        }
        else return false;
    }

    //populates the patientEntry object fields based on the csvFormat string
    public void populateFromCSV()
    {
        String info[] = csvFormat.split(",");
        lastName = info[0];
        firstName = info[1];
        birthdate = info[2];
    }
}
