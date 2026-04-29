package individual1;

import java.io.*;
import java.util.Scanner;

public class Login extends Account {

    String printEmailLogin(Scanner in) {
        System.out.print("Enter your gmail: ");
        String email = in.nextLine();
        setEmail(email);
        return email;
    }

    String printPasswordLogin(Scanner in) {
        Console console = System.console();
        String password;

        if (console != null) {
            password = new String(console.readPassword("Enter your password: "));
        } else {
            System.out.print("Enter your password: ");
            password = in.nextLine();
        }

        setPassword(password);
        return password;
    }

    boolean loginUser() {
        Scanner in = new Scanner(System.in);

        String email = printEmailLogin(in);
        String password = printPasswordLogin(in);

        try (Scanner fileScanner = new Scanner(new FileInputStream("users.txt"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 8) {
                    String nameFound = parts[0];
                    String lastNameFound = parts[1];
                    String emailFound = parts[2];
                    String passwordFound = parts[3];
                    String idFound = parts[4];
                    String grades[] = parts[5].split(",");
                    String enrolledCourses[] = parts[6].split(",");

                    if (emailFound.equals(email) && passwordFound.equals(password)) {
                        setName(nameFound);
                        setLastName(lastNameFound);
                        setEmail(emailFound);
                        setPassword(passwordFound);
                        setIdMember(idFound);

                        return true;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

    void printAllInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Last name: " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("ID: " + getIdMember());

    }
}
