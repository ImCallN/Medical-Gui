public class UserEntry 
{
    String firstName;
    String lastName;
    String birthdate; 
    
    public void populateEntry(String last, String first, String birth)
    {
        lastName = last;
        firstName = first;
        birthdate = birth;
    }

    public void displayUser()
    {
        System.out.println(this.lastName + ", " + this.firstName + ", " + this.birthdate);
    }
}
