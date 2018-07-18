package com.evolution.game;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeSet;

public class BestResults {
    private static final BestResults ourInstance = new BestResults();

    public static BestResults getInstance() {
        return ourInstance;
    }

    private TreeSet<Records> allResult=new TreeSet(new RecordsComparator());

    private Records lastResult;

    public Records getLastResult() {
        return lastResult;
    }

    public void setLastResult(Records lastResult) {
        this.lastResult = lastResult;
    }

    private BestResults() {
        //считываем данные из файла
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(Gdx.files.local("records.dat").read());
            while(true) {
                allResult.add((Records) in.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //метод возвращает 5 лучших результатов
    public ArrayList<Records> getBest5(){
        ArrayList <Records> best5=new ArrayList<Records>();
        int i=0;
        for(Records r:allResult){
            best5.add(r);
            i++;
            if(i>=5){
                break;
            }
        }
        return  best5;
    }

    //метод сохраняет в файл
    public void save(){
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(Gdx.files.local("records.dat").write(false));
            int i=0;
            for(Records r:allResult) {
                out.writeObject(r);
                i++;
                if(i>=5){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void add (Records r){
        allResult.add(r);
    }
}
