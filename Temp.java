package individual1;

import java.util.Scanner;

public class Temp extends Account {

    void print() {
        System.out.print("Enter your password: ");

        Scanner in = new Scanner(System.in);
        while (in.nextInt() != 0) {
            setPassword(passwordHash);
        }
        in.next();
    }

    public static void main(String[] args) {
        Temp temp = new Temp();

        temp.print();
    }
}
