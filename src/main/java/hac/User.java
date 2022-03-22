package hac;

/**
 * a sample class to hold some data to of our application
 */
public class User {

    private String lastName;
    private String firstName;

    public User(String firstName, String lastName) {
        // if no firstname or lastname is given, throw an exception
        if ((firstName == null && firstName.length() == 0)
                || (lastName == null || lastName.length() == 0)) {
            throw new IllegalArgumentException("firstName and lastName must not be empty");
        }

        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

}