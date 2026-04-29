package individual1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Login extends Account {

    private String[] grades = new String[0];
    private String[] enrolledCourses = new String[0];

    public String[] getGrades() {
        return grades;
    }

    public String[] getEnrolledCourses() {
        return enrolledCourses;
    }

    String printEmailLogin(Scanner in) {
        System.out.print("Enter your gmail: ");
        String email = in.nextLine();
        setEmail(email);
        return email;
    }

    String printPasswordLogin(Scanner in) {
        System.out.print("Enter your password: ");
        String password = in.nextLine();
        setPassword(password);
        return password;
    }

    boolean loginUser(Scanner in) {
        String email = printEmailLogin(in);
        String password = printPasswordLogin(in);

        try (Scanner fileScanner = new Scanner(new FileInputStream("users.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

<<<<<<< HEAD
                String[] parts = line.split(":", -1);

                if (parts.length >= 7) {
                    String nameFound = parts[0];
                    String lastNameFound = parts[1];
                    String emailFound = parts[2];
                    String passwordFound = parts[3];
                    String idFound = parts[4];

                    if (emailFound.equals(email) && passwordFound.equals(password)) {
                        setName(nameFound);
                        setLastName(lastNameFound);
                        setEmail(emailFound);
                        setPassword(passwordFound);
                        setIdMember(idFound);

                        if (parts[5].isEmpty()) {
                            grades = new String[0];
                        } else {
                            grades = parts[5].split(",");
                        }

                        if (parts[6].isEmpty()) {
                            enrolledCourses = new String[0];
                        } else {
                            enrolledCourses = parts[6].split(",");
                        }

=======
                if (parts.length >= 4) {
                    String emailFound = parts[2];
                    String passwordFound = parts[3];

                    if (emailFound.equals(email) && passwordFound.equals(password)) {
>>>>>>> parent of 0f1a382 (login && registration)
                        return true;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Cannot read users.txt");
        }

        return false;
    }
}
