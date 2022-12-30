package com.example.mvcdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Issue {
    @Id
    private Long id;
    private String created_at;
    private String closed_at;
    private String state;

    private String title;

    public Issue() {
    }

    public Issue(Long id, String created_at, String closed_at, String state, String title) {
        this.id = id;
        this.created_at = created_at;
        this.closed_at = closed_at;
        this.state = state;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", created_at='" + created_at + '\'' +
                ", closed_at='" + closed_at + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
