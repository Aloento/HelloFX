package com.QYun.HelloFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class P_Student {

    private String name;
    private int age;
//    private double score;

    private SimpleStringProperty P_name;
    private SimpleIntegerProperty P_age;
//    private SimpleDoubleProperty P_score;

    public P_Student (String name, int age) {
        if (P_name == null)
            this.name = name;
        else this.P_name.set(name);

        if (P_age == null)
            this.age = age;
        else this.P_age.set(age);
    }

    public String getName() {
        if (P_name == null)
            return this.name;
        else return this.P_name.get();
    }

    public SimpleStringProperty nameProperty() {
        if (P_name == null)
            P_name = new SimpleStringProperty(this, "name", name);
        return P_name;
    }

    public void setName(String name) {
        if (P_name == null)
            this.name = name;
        else this.P_name.set(name);
    }

    public int getAge() {
        if (P_age == null)
            return this.age;
        else return this.P_age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        if (P_age == null)
            P_age = new SimpleIntegerProperty(this, "age", age);
        return P_age;
    }

    public void setAge(int age) {
        if (P_age == null)
            this.age = age;
        else this.P_age.set(age);
    }

}
//Written by Aloento.