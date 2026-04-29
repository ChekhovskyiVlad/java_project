package individual1;

<<<<<<< HEAD
<<<<<<< HEAD
import java.io.*;
import java.util.*;
=======
=======
>>>>>>> parent of 0f1a382 (login && registration)
/// все дальнейшие значение будут просто дописываться уже к существующему аккаунта, к примеру какие курсы, какие оценки
// может тогда имеет смысл 

/*
когда юзер заходит в свой аккаунт, просто подтягиваются его оценки сразу с бинарника, вот и все

то есть после того как юзер залогинился ему на панель отображается что за оценки у него на какие курсы он записан

лучше всего полностью строку выность
*/

>>>>>>> parent of 0f1a382 (login && registration)

public class Teacher extends Account {

    private ArrayList<Course> courses = new ArrayList<>();

<<<<<<< HEAD
<<<<<<< HEAD
    void printPanel() {
        Scanner in = new Scanner(System.in);

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
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    createCourse(in);
                    break;

                case 2:
                    assignGradeToStudent(in);
                    break;

                case 3:
                    createTask(in);
                    break;

                case 4:
                    enrollStudentToCourse(in);
                    break;

                case 5:
                    showCourses();
                    break;

                case 6:
                    System.out.println("Exit...");
                    return;

                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    void createCourse(Scanner in) {
        Course course = new Course();

        System.out.print("Enter course title: ");
        String title = in.nextLine();

        course.setTitle(title);
        course.setTeacherIds(new String[]{getIdMember()});
        course.setStudentIds(new String[0]);
        course.setTasks(new String[0]);

        courses.add(course);
        saveCourseToFile(course);

        System.out.println("Course created successfully!");
    }

    void assignGradeToStudent(Scanner in) {
        System.out.print("Enter student ID: ");
        String studentId = in.nextLine();

        System.out.print("Enter grade: ");
        String grade = in.nextLine();

        ArrayList<String> users = readLinesFromFile("users.txt");
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            String line = users.get(i);
            String[] parts = line.split(":", -1);

            if (parts.length >= 7 && parts[4].equals(studentId)) {
                String oldGrades = parts[5];

                if (oldGrades.isEmpty()) {
                    parts[5] = grade;
                } else {
                    parts[5] = oldGrades + "," + grade;
                }

                users.set(i, String.join(":", parts));
                found = true;
                break;
            }
        }

        if (found) {
            writeLinesToFile("users.txt", users);
            System.out.println("Grade was added successfully.");
        } else {
            System.out.println("Student wasn't found.");
        }
    }

    void createTask(Scanner in) {
        System.out.print("Enter course title: ");
        String courseTitle = in.nextLine();

        System.out.print("Enter task: ");
        String task = in.nextLine();

        ArrayList<String> courses = readLinesFromFile("courses.txt");
        boolean found = false;

        for (int i = 0; i < courses.size(); i++) {
            String line = courses.get(i);
            String[] parts = line.split(":", -1);

            if (parts.length >= 4 && parts[0].equals(courseTitle)) {
                String oldTasks = parts[3];

                if (oldTasks.isEmpty()) {
                    parts[3] = task;
                } else {
                    parts[3] = oldTasks + "," + task;
                }

                courses.set(i, String.join(":", parts));
                found = true;
                break;
            }
        }

        if (found) {
            writeLinesToFile("courses.txt", courses);
            System.out.println("Task was added successfully.");
        } else {
            System.out.println("Course wasn't found.");
        }
    }

    void enrollStudentToCourse(Scanner in) {
        System.out.print("Enter course title: ");
        String courseTitle = in.nextLine();

        System.out.print("Enter student ID: ");
        String studentId = in.nextLine();

        boolean courseUpdated = addStudentToCourse(courseTitle, studentId);
        boolean userUpdated = addCourseToStudent(studentId, courseTitle);

        if (courseUpdated && userUpdated) {
            System.out.println("Student was enrolled successfully.");
        } else {
            System.out.println("Course or student wasn't found.");
        }
    }

    void showCourses() {
        ArrayList<String> courses = readLinesFromFile("courses.txt");

        boolean found = false;

        for (String line : courses) {
            String[] parts = line.split(":", -1);

            if (parts.length >= 4) {
                String title = parts[0];
                String teacherIds = parts[1];
                String studentIds = parts[2];
                String tasks = parts[3];

                if (teacherIds.contains(getIdMember())) {
                    found = true;

                    System.out.println("Course: " + title);
                    System.out.println("Students: " + studentIds);
                    System.out.println("Tasks: " + tasks);
                    System.out.println("----------------------");
                }
            }
        }

        if (!found) {
            System.out.println("You don't have courses yet.");
        }
    }

    private void saveCourseToFile(Course course) {
        try (FileOutputStream fos = new FileOutputStream("courses.txt", true)) {
            String teachers = String.join(",", course.getTeacherIds());
            String students = "";
            String tasks = "";

            String data = course.getTitle() + ":" + teachers + ":" + students + ":" + tasks + "\n";
            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean addStudentToCourse(String courseTitle, String studentId) {
        ArrayList<String> courses = readLinesFromFile("courses.txt");
        boolean found = false;

        for (int i = 0; i < courses.size(); i++) {
            String line = courses.get(i);
            String[] parts = line.split(":", -1);

            if (parts.length >= 4 && parts[0].equals(courseTitle)) {
                String oldStudents = parts[2];

                if (oldStudents.isEmpty()) {
                    parts[2] = studentId;
                } else {
                    parts[2] = oldStudents + "," + studentId;
                }

                courses.set(i, String.join(":", parts));
                found = true;
                break;
            }
        }

        if (found) {
            writeLinesToFile("courses.txt", courses);
        }

        return found;
    }

    private boolean addCourseToStudent(String studentId, String courseTitle) {
        ArrayList<String> users = readLinesFromFile("users.txt");
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            String line = users.get(i);
            String[] parts = line.split(":", -1);

            if (parts.length >= 7 && parts[4].equals(studentId)) {
                String oldCourses = parts[6];

                if (oldCourses.isEmpty()) {
                    parts[6] = courseTitle;
                } else {
                    parts[6] = oldCourses + "," + courseTitle;
                }

                users.set(i, String.join(":", parts));
                found = true;
                break;
            }
        }

        if (found) {
            writeLinesToFile("users.txt", users);
        }

        return found;
    }

    private ArrayList<String> readLinesFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException ex) {
            System.out.println("Cannot read file: " + filename);
        }

        return lines;
    }

    private void writeLinesToFile(String filename, ArrayList<String> lines) {
        try (FileOutputStream fos = new FileOutputStream(filename, false)) {
            for (String line : lines) {
                fos.write((line + "\n").getBytes());
            }
        } catch (IOException ex) {
            System.out.println("Cannot write file: " + filename);
        }
    }
=======
>>>>>>> parent of 0f1a382 (login && registration)
=======
>>>>>>> parent of 0f1a382 (login && registration)
}
