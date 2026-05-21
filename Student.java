package individual1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Student extends Account {

    public Student(Account currentUser) {
        setStatus(currentUser.getStatus());
        setName(currentUser.getName());
        setLastName(currentUser.getLastName());
        setEmail(currentUser.getEmail());
        setPassword(currentUser.getPassword());
        setIdMember(currentUser.getIdMember());
    }

    void printInfoStudent() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. See all grades and feedback
                    2. Do tasks
                    3. Calculate your average grade
                    4. Update your data
                    5. Exit
                    """);

            System.out.print("Choose option: ");
            short choice = in.nextShort();
            in.nextLine();

            switch (choice) {
                case 1 ->
                    seeGradesFeedBack();
                case 2 ->
                    doTasks(in);
                case 3 ->
                    calculateAverageGrade();
                case 4 ->
                    System.out.println("Update data function is not ready yet.");
                case 5 -> {
                    System.out.println("Exit...");
                    return;
                }
                default ->
                    System.out.println("Wrong choice.");
            }
        }
    }

    private void doTasks(Scanner in) {
        String studentCourses = findStudentCourses(getIdMember());

        if (studentCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        String[] courses = studentCourses.split(",");
        boolean hasTasks = false;

        try (Scanner fileScanner = new Scanner(new FileInputStream("tasks.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 4) {
                    String taskCourseTitle = parts[0];
                    String taskTitle = parts[1];
                    String description = parts[2];
                    String exercise = parts[3];

                    if (studentHasCourse(courses, taskCourseTitle)) {
                        hasTasks = true;

                        System.out.println();
                        System.out.println("Course: " + taskCourseTitle);
                        System.out.println("Task: " + taskTitle);
                        System.out.println("Description: " + description);
                        System.out.println("Exercise: " + exercise);

                        System.out.print("Do you want to answer this task? yes/no: ");
                        String answerChoice = in.nextLine();

                        if (answerChoice.equalsIgnoreCase("yes")) {
                            writeAnswer(in, taskCourseTitle, taskTitle);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading tasks.txt: " + ex.getMessage());
            return;
        }

        if (!hasTasks) {
            System.out.println("There are no tasks for your courses.");
        }
    }

    String findStudentCourses(String studentId) {
        try (Scanner fileScanner = new Scanner(new FileInputStream("users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 7 && parts[5].equals(studentId)) {
                    return parts[6];
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading users.txt: " + ex.getMessage());
        }

        return "";
    }

    boolean studentHasCourse(String[] courses, String taskCourseTitle) {
        for (String course : courses) {
            if (course.trim().equalsIgnoreCase(taskCourseTitle.trim())) {
                return true;
            }
        }

        return false;
    }

    void writeAnswer(Scanner in, String courseTitle, String taskTitle) {
        System.out.print("Enter your answer: ");
        String answer = in.nextLine();

        Submission submission = new Submission(
                courseTitle,
                taskTitle,
                getIdMember(),
                answer
        );

        submission.save();

        System.out.println("Answer submitted successfully.");
    }

    void seeGradesFeedBack() {
        String studentId = getIdMember();
        boolean hasGrades = false;

        try (Scanner scanner = new Scanner(new FileInputStream("submission.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                /*
                submission.txt:
                0 courseTitle
                1 taskTitle
                2 studentId
                3 answer
                4 status
                5 grade
                6 feedback
                 */
                if (parts.length >= 7 && parts[2].equals(studentId)) {
                    hasGrades = true;

                    System.out.println();
                    System.out.println("Course: " + parts[0]);
                    System.out.println("Task: " + parts[1]);
                    System.out.println("Answer: " + parts[3]);
                    System.out.println("Status: " + parts[4]);
                    System.out.println("Grade: " + parts[5]);
                    System.out.println("Feedback: " + parts[6]);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading submission.txt: " + ex.getMessage());
            return;
        }

        if (!hasGrades) {
            System.out.println("You don't have checked submissions yet.");
        }
    }

    void calculateAverageGrade() {
        String studentId = getIdMember();

        int sum = 0;
        int count = 0;

        try (Scanner scanner = new Scanner(new FileInputStream("submission.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                if (parts.length >= 7 && parts[2].equals(studentId)) {
                    String gradeText = parts[5];

                    try {
                        int grade = Integer.parseInt(gradeText);

                        if (grade > 0) {
                            sum += grade;
                            count++;
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid grade format: " + gradeText);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading submission.txt: " + ex.getMessage());
            return;
        }

        if (count == 0) {
            System.out.println("You don't have grades yet.");
        } else {
            double average = (double) sum / count;
            System.out.println("Average grade: " + average);
        }
    }
}
