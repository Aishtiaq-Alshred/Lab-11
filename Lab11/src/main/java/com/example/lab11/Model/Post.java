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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = "content should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String content;

    @NotNull(message = "category id should not be empty")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer category_id;

    @NotNull(message = "publication date should not be empty")
    @Column(columnDefinition = " not null")
    private Date publication_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "title should not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "title should not be empty") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "content should not be empty") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "content should not be empty") String content) {
        this.content = content;
    }



    public @NotNull(message = "category id should not be empty") Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(@NotNull(message = "category id should not be empty") Integer category_id) {
        this.category_id = category_id;
    }

    public @NotNull(message = "publication date should not be empty") Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(@NotNull(message = "publication date should not be empty") Date publication_date) {
        this.publication_date = publication_date;
    }

    public Post(Integer id, String title, String content, Integer category_id, Date publication_date) {
        this.id = id;
        this.title = title;
        this.content = content;

        this.category_id = category_id;
        this.publication_date = publication_date;
    }

    public Post() {

    }
}
