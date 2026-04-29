package individual1;

import java.util.Scanner;

public class Student extends Account {

    private long idStudent;
    private short grades[];
    public String enrolledCourses[];

    void printInfoStudent() {

        short choice;
        String text = """
                1. See all grades
                2. Do tasks
                3. Calculate your average grade

                """;
        System.out.print(text);

        Scanner in = new Scanner(System.in);
        choice = in.nextShort();

        switch (choice) {
            case 1:

            case 2:

            case 3:
                break;
            default:
                throw new AssertionError();
        }

        in.close();
    }
}
