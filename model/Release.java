package com.example.mvcdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Release {
    @Id
    private Long id;
    private String published_at;


    public Release() {
    }

    public Release(Long id, String published_at) {
        this.id = id;
        this.published_at = published_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    @Override
    public String toString() {
        return "Release{" +
                "id=" + id +
                ", published_at='" + published_at + '\'' +
                '}';
    }
}
