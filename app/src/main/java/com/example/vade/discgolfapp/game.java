package com.example.vade.discgolfapp;

import java.io.Serializable;
import java.util.List;

public class game implements Serializable {

    private int id;
    private player player;
    private course course;
    private int score;
    private List<Integer> scoreList;
    private int players;

        public game (int id, player pelaaja, course rata, int score, List scoreList) {
            this.setId(id);
            this.setPlayer(pelaaja);
            this.setCourse(rata);
            this.setScore(score);
            this.setScoreList(scoreList);
        }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.example.vade.discgolfapp.player getPlayer() {
        return player;
    }

    public void setPlayer(com.example.vade.discgolfapp.player player) {
        this.player = player;
    }

    public com.example.vade.discgolfapp.course getCourse() {
        return course;
    }

    public void setCourse(com.example.vade.discgolfapp.course course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List getScoreList() {
        return scoreList;
    }

    public void setScoreList(List scoreList) {
        this.scoreList = scoreList;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
