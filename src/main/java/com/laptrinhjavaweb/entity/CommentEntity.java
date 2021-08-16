package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_Id")
    private NewEntity newEntity;

    public String getContent() {
        return content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewEntity getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(NewEntity newEntity) {
        this.newEntity = newEntity;
    }
}
