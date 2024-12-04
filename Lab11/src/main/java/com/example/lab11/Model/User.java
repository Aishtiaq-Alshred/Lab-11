package com.example.lab11.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotEmpty(message = "username should not be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String username;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "must be valid email")
    @Column(columnDefinition = "varchar(20) not null")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotNull(message = "registration date should not be empty")
    @Column(columnDefinition = "not null")
    private Date regisration_date;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public @NotEmpty(message = "username should not be empty") @Size(min = 4, message = "username should not be less than 4") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "username should not be empty") @Size(min = 4, message = "username should not be less than 4") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "email should not be empty") @Email(message = "must be valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email should not be empty") @Email(message = "must be valid email") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "password should not be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password should not be empty") String password) {
        this.password = password;
    }

    public @NotNull(message = "Registration Date should not be empty") Date getRegisration_date() {
        return regisration_date;
    }

    public void setRegisration_date(@NotNull(message = "Registration Date should not be empty") Date regisration_date) {
        this.regisration_date = regisration_date;
    }

    public User(Integer user_id, String username, String email, String password, Date regisration_date) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.regisration_date = regisration_date;
    }

    public User() {

    }
}
