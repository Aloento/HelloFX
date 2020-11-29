package com.QYun.HelloFX;

import java.beans.PropertyChangeSupport;

public class Student {

    private String name;
    private int age;
    private double score;

    public PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        pcs.firePropertyChange("setName_Pro", oldValue, this.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        int oldValue = this.age;
        this.age = age;
        pcs.firePropertyChange("setAge_Pro", oldValue, this.age);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        double oldValue = this.score;
        this.score = score;
        pcs.firePropertyChange("setScore", oldValue, this.score);
    }

    @Override
    public String toString() {
        return name + "：" + age + "岁";
    }
}
