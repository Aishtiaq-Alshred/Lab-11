package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "post id should not be empty")
    @Column(columnDefinition = "int not null")
    @Positive
    private Integer post_id;

    @NotNull(message = "user id should not be empty")
    @Column(columnDefinition = "int not null")
    @Positive
    private Integer user;

    @NotEmpty(message = "content should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String content;

    @NotNull(message = "publication date should not be empty")
    @Column(columnDefinition = "not null")
    private Date comment_date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "post id should not be empty") Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(@NotNull(message = "post id should not be empty") Integer post_id) {
        this.post_id = post_id;
    }



    public @NotEmpty(message = "content should not be empty") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "content should not be empty") String content) {
        this.content = content;
    }

    public @NotNull(message = "publication date should not be empty") Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(@NotNull(message = "publication date should not be empty") Date comment_date) {
        this.comment_date = comment_date;
    }

    public Comment(Integer id, Integer post_id, String content, Date comment_date) {
        this.id = id;
        this.post_id = post_id;

        this.content = content;
        this.comment_date = comment_date;
    }

    public Comment() {

    }

    public @NotNull(message = "user id should not be empty") Integer getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "user id should not be empty") Integer user) {
        this.user = user;
    }
}
