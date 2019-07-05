package com.example.newsfeed;

public class livemodel {
    String home;
    String away;
    String score;
    String time;
    String status;
    String leaguename;
    public livemodel(String home, String away, String score, String time, String status, String leaguename) {
        this.home = home;
        this.away = away;
        this.score = score;
        this.time = time;
        this.status = status;
        this.leaguename = leaguename;
    }


    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }


}
