package com.example.vade.discgolfapp;

import java.io.Serializable;
import java.util.List;

public class player implements Serializable {

    private int id;
    private String name;
    private List<String> coursesByName;
    private int bestScore;
    private List games;

    public player(int id,String name) {
        this.setId(id);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getCourses() {
        return coursesByName;
    }

    public void setCourses(List courses) {
        this.coursesByName = courses;
    }

    public void addCourse(course Course) {
        this.coursesByName.add(Course.getName());
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public List getGames() {
        return games;
    }

    public void setGames(List games) {
        this.games = games;
    }
    public void addGame(game Game) {
        this.games.add(Game);
    }
}
