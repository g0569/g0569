package com.example.g0569.scoreboard.model;

import java.util.Date;

public class Score {
    private Date createdTime;
    private int scoreId;
    private int uid;
    private int score;

    public Score(int uid, int score) {
        this.uid = uid;
        this.score = score;
    }

    public Score(Date createdTime, int scoreId, int uid, int score) {
        this.createdTime = createdTime;
        this.scoreId = scoreId;
        this.uid = uid;
        this.score = score;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
