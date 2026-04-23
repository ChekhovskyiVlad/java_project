package individual1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String text = """
                Input your choice:
                1. Sign Up
                2. Sign In
                """;

        System.out.print(text);

        Scanner in = new Scanner(System.in);
        short choice = in.nextShort();
        in.nextLine();

        Registration reg = new Registration();

        if (choice == 1) {
            reg.printEmail(in);

            if (reg.proveEmail()) {
                reg.printPassword();
                reg.generateMemberId();
                reg.saveUser();
            } else {
                System.out.println("Email is invalid");
            }
        } else if (choice == 2) {
            System.out.println("Sign In function will be here");
        } else {
            System.out.println("Invalid choice");
        }

        in.close();
    }
}
