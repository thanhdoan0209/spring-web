package com.laptrinhjavaweb.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "new")
@EntityListeners(AuditingEntityListener.class)
public class NewEntity extends BaseEntity{

    @Column(name = "title")
    private  String title;

    @Column(name = "thumbnail")
    private  String thumbnail;

    @Column(name = "shortdescription", columnDefinition = "TEXT")
    private  String shortDescription;

    @Column(name = "content", columnDefinition = "TEXT")
    private  String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "newEntity")
    private List<CommentEntity> comments = new ArrayList<>();

    public CategoryEntity getCategory() {
        return category;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
