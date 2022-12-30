package com.example.mvcdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Commit {
    @Id
    private String id;
    private String date;



    public Commit(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public Commit() {

    }
}
