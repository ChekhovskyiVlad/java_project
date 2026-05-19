package individual1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account {

    private ArrayList<Course> courses = new ArrayList<>();

    public Teacher(Account currentUser) {
        setStatus(currentUser.getStatus());
        setName(currentUser.getName());
        setLastName(currentUser.getLastName());
        setEmail(currentUser.getEmail());
        setPassword(currentUser.getPassword());
        setIdMember(currentUser.getIdMember());
    }

    void printInfoTeacher() {
        Scanner in = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    1. Create a course
                    2. Assign grade to a student
                    3. Add a task to the course
                    4. Enroll a student to the course
                    5. Show my courses
                    6. Exit
                    """);

            System.out.print("Choose option: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1 ->
                    createCourse(in);
                case 2 ->
                    assignGradeToStudent(in);
                case 3 ->
                    createTask(in);
                case 4 ->
                    enrollStudentToExistingCourse(in);
                case 5 ->
                    showCourses();
                case 6 -> {
                    System.out.println("Exit...");
                    return;
                }
                default ->
                    System.out.println("Wrong choice");
            }
        }
    }

    void createCourse(Scanner in) {
        Course course = new Course();

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        course.setTitle(title);
        course.setTeacherId(getIdMember());

        System.out.print("Do you want to add a student now? yes/no: ");
        String answer = in.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            enrollStudentToCourse(in, course);
        }

        courses.add(course);
        saveCourse(course);

        System.out.println("Course created successfully!");
    }

    void enrollStudentToCourse(Scanner in, Course course) {
        System.out.print("Enter student ID: ");
        String studentId = in.nextLine().trim();

        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        if (studentExists(studentId)) {
            course.addStudentId(studentId);
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Student with this ID was not found.");
        }
    }

    void enrollStudentToExistingCourse(Scanner in) {
        if (courses.isEmpty()) {
            System.out.println("You have no courses.");
            return;
        }

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        Course course = findCourseByTitle(title);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        enrollStudentToCourse(in, course);
        rewriteCoursesFile();
    }

    boolean studentExists(String studentId) {
        try (Scanner scanner = new Scanner(new FileInputStream("users.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");

                // Предположим, что ID пользователя хранится в parts[0],
                // а статус student/teacher в parts[1]
                if (parts.length >= 5) {
                    String id = parts[5];
                    String status = parts[0];

                    if (id.equals(studentId) && status.equalsIgnoreCase("student")) {
                        return true;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Error reading users file: " + ex.getMessage());
        }

        return false;
    }

    Course findCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }
        return null;
    }

    void saveCourse(Course course) {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", true)) {
            String students = String.join(",", course.getStudentIds());

            String data = course.getTitle() + ":"
                    + course.getTeacherId() + ":"
                    + students + "\n";

            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println("Error writing file: " + ex.getMessage());
        }
    }

    void rewriteCoursesFile() {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", false)) {

            for (Course course : courses) {
                String students = String.join(",", course.getStudentIds());

                String data = course.getTitle() + ":"
                        + course.getTeacherId() + ":"
                        + students + "\n";

                fos.write(data.getBytes());
            }

        } catch (IOException ex) {
            System.out.println("Error writing file: " + ex.getMessage());
        }
    }

    void assignGradeToStudent(Scanner in) {
        System.out.print("Enter course title: ");
        String title = in.nextLine();

        Course course = findCourseByTitle(title);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter student ID: ");
        String studentId = in.nextLine();

        if (!course.getStudentIds().contains(studentId)) {
            System.out.println("This student is not enrolled in this course.");
            return;
        }

        System.out.print("Enter grade: ");
        String grade = in.nextLine();

        System.out.println("Grade " + grade + " assigned to student " + studentId);
    }

    void createTask(Scanner in) {
        System.out.print("Enter course title: ");
        String title = in.nextLine();

        Course course = findCourseByTitle(title);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Task task = new Task();
        System.out.print("Enter task title: ");
        String taskTitle = in.nextLine();
        task.setTitle(taskTitle);

        System.out.print("Enter the description: ");
        String input = in.nextLine();
        String[] description = input.split(",");
        task.setDescription(description);

        System.out.print("Enter the exercise: ");
        String[] exercise = input.split(",");
        task.setExercise(exercise);

        System.out.println("Task created: " + taskTitle);
    }

    void showCourses() {
        if (courses.isEmpty()) {
            System.out.println("You have no courses.");
            return;
        }

        for (Course course : courses) {
            System.out.println("Course: " + course.getTitle());
            System.out.println("Teacher ID: " + course.getTeacherId());
            System.out.println("Students: " + course.getStudentIds());
        }
    }
}
