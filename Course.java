package individual1;

public class Course {

    private String title;
    private String[] studentIds;
    private String teacherId;
    private String[] tasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String[] studentIds) {
        this.studentIds = studentIds;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherIds(String teacherId) {
        this.teacherId = teacherId;
    }

    public String[] getTasks() {
        return tasks;
    }

    public void setTasks(String[] tasks) {
        this.tasks = tasks;
    }

}
