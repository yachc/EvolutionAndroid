package com.evolution.game;

import java.util.Comparator;

public class RecordsComparator implements Comparator  <Records> {

    @Override
    public int compare(Records r1, Records r2) {
        if(r1.getScore()>r2.getScore()){
            return -1;
        } else if(r1.getScore()<r2.getScore()){
            return 1;
        } else{
            if(r1.getLvl()>r2.getLvl()){
                return -1;
            } else if(r1.getLvl()<r2.getLvl()){
                return 1;
            } else{
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}
