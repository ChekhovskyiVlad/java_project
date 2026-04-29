package individual1;

public class Student extends Account {

    private String[] grades = new String[0];
    private String[] enrolledCourses = new String[0];

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

    public String[] getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(String[] enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

<<<<<<< HEAD
    void printInfoStudent() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. See all grades
                    2. See enrolled courses
                    3. Calculate your average grade
                    4. Show my info
                    5. Exit
                    """);

            System.out.print("Choose option: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    showGrades();
                    break;

                case 2:
                    showCourses();
                    break;

                case 3:
                    calculateAverageGrade();
                    break;

                case 4:
                    showStudentInfo();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    void showGrades() {
        if (grades.length == 0 || grades[0].isEmpty()) {
            System.out.println("You don't have grades yet.");
            return;
        }

        System.out.println("Your grades:");
        for (String grade : grades) {
            if (!grade.isEmpty()) {
                System.out.println(grade);
            }
        }
    }

    void showCourses() {
        if (enrolledCourses.length == 0 || enrolledCourses[0].isEmpty()) {
            System.out.println("You are not enrolled in courses yet.");
            return;
        }

        System.out.println("Your courses:");
        for (String course : enrolledCourses) {
            if (!course.isEmpty()) {
                System.out.println(course);
            }
        }
    }

    void calculateAverageGrade() {
        if (grades.length == 0 || grades[0].isEmpty()) {
            System.out.println("No grades to calculate.");
            return;
        }

        double sum = 0;
        int count = 0;

        for (String grade : grades) {
            if (!grade.isEmpty()) {
                sum += Double.parseDouble(grade);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No grades to calculate.");
        } else {
            System.out.println("Average grade: " + (sum / count));
        }
    }

    void showStudentInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Last name: " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("ID: " + getIdMember());

        showCourses();
        showGrades();
    }
=======
>>>>>>> parent of 0f1a382 (login && registration)
}
