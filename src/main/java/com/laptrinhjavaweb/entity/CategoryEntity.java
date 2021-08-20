package com.laptrinhjavaweb.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends  BaseEntity{

    @Column
    private String name;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "category")
    private List<NewEntity> news = new ArrayList<>();

    public List<NewEntity> getNews() {
        return news;
    }

    public void setNews(List<NewEntity> news) {
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
