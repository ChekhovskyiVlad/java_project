package individual1;

public class Course {

    private String title;
    private String[] studentIds;
    private String[] teacherIds;
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

    public String[] getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String[] teacherIds) {
        this.teacherIds = teacherIds;
    }

    public String[] getTasks() {
        return tasks;
    }

    public void setTasks(String[] tasks) {
        this.tasks = tasks;
    }
}
