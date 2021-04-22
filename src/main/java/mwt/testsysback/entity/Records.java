package mwt.testsysback.entity;

public class Records {
    private int record_id;
    private int assign_id;
    private String title;
    private String username;
    private String nickname;
    private String date;
    private String file_source;
    private String file_report;
    private int score;
    private int weight;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int assign_id) {
        this.record_id = record_id;
    }

    public int getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(int assign_id) {
        this.assign_id = assign_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFile_source() {
        return file_source;
    }

    public void setFile_source(String file_source) {
        this.file_source = file_source;
    }

    public String getFile_report() {
        return file_report;
    }

    public void setFile_report(String file_report) {
        this.file_report = file_report;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
