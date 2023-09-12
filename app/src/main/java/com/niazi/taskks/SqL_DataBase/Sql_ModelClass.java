package com.niazi.taskks.SqL_DataBase;

import java.util.ArrayList;

public class Sql_ModelClass {

    String id, name,surname,marks;

    public Sql_ModelClass(String id, String name, String surname, String marks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
