package mwt.testsysback.entity;

public class Collection {

    private int collection_id;
    private String collection_name;
    private String teachers;
    private String students;

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int id) {
        this.collection_id = id;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
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
