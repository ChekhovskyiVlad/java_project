package individual1;

import java.io.FileOutputStream;
import java.io.IOException;

public class Submission {

    private String courseTitle;
    private String taskTitle;
    private String studentId;
    private String answer;

    private String status = "PENDING";
    private int grade = 0;
    private String feedback = "-";

    public Submission(String courseTitle, String taskTitle, String studentId, String answer) {
        this.courseTitle = courseTitle;
        this.taskTitle = taskTitle;
        this.studentId = studentId;
        this.answer = answer;
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream("submission.txt", true)) {

            String data = courseTitle + ":"
                    + taskTitle + ":"
                    + studentId + ":"
                    + answer + ":"
                    + status + ":"
                    + grade + ":"
                    + feedback + "\n";

            fos.write(data.getBytes());

        } catch (IOException ex) {
            System.out.println("Error writing submission: " + ex.getMessage());
        }
    }
}
