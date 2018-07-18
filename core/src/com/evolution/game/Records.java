package com.evolution.game;

import java.io.Serializable;
import java.util.Date;

public class Records implements Serializable {
    private String name;
    private int score;
    private int lvl;
    private Date date;

    public Records(String name, int score, int lvl, Date date) {
        this.name = name;
        this.score = score;
        this.lvl = lvl;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLvl() {
        return lvl;
    }

    public Date getDate() {
        return date;
    }
}
