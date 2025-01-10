package com.simpleblog.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private LocalDate timePostCreated;

    public Post(){}

    public Post(String title, String content, LocalDate timePostCreated){
        this.title = title;
        this.content = content;
        this.timePostCreated = timePostCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    } 

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @NotBlank String getContent() {
        return content;
    }

    public void setContent(@NotBlank String content) {
        this.content = content;
    }

    public LocalDate getLocalDate(){
        return timePostCreated;
    }

    public void setLocalDate(LocalDate timePostCreated) {
        this.timePostCreated = timePostCreated;
    }    
}
