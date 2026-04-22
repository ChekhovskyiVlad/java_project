package individual1;

import java.util.Scanner;

public class Temp {

    void print() {
        System.out.print("Enter your password: ");

        Scanner in = new Scanner(System.in);

        in.next();
    }

    public static void main(String[] args) {
        Temp temp = new Temp();

        temp.print();
    }
}
