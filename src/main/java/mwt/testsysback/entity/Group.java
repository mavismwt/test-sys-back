package mwt.testsysback.entity;

public class Group {

    private int group_id;
    private String group_name;
    private String teachers;
    private String students;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int id) {
        this.group_id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

}
