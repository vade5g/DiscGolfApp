package com.example.vade.discgolfapp;

import java.io.Serializable;
import java.util.List;

public class course implements Serializable {

    private String name;
    private int parScore;
    private int holesNumber;
    private List<Integer> holes;

    public course (String name, int parScore, int numberOfHoles,List holes) {
        this.setName(name);
        this.setParScore(parScore);
        this.setHolesNumber(numberOfHoles);
        this.setHoles(holes);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParScore() {
        return parScore;
    }

    public void setParScore(int parScore) {
        this.parScore = parScore;
    }

    public int getHolesNumber() {
        return holesNumber;
    }

    public void setHolesNumber(int holesNumber) {
        this.holesNumber = holesNumber;
    }

    public List getHoles() {
        return holes;
    }

    public void setHoles(List holes) {
        this.holes = holes;
    }
}