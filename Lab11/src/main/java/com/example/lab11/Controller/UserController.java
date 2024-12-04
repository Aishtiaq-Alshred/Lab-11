package com.example.lab11.Controller;

import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/User")
//@RequiredArgsConstructor

public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get")
    public ResponseEntity getUsers(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@Valid@RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }



    @PutMapping("/update/{user_id}")
    public ResponseEntity updateUser(@PathVariable Integer user_id ,@Valid@RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.updateUser(user_id, user);
        return ResponseEntity.status(HttpStatus.OK).body("user update");

    }


    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Integer user_id){

        userService.deleteUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted");
    }



    @GetMapping("/checkUser/{username}/{password}")
    public ResponseEntity checkUser(@PathVariable String username , @PathVariable String password){
        userService.checkUser(username, password);
        return ResponseEntity.status(HttpStatus.OK).body("valid username and password");
    }



    @PutMapping("/changeUsername/{id}/{username}")
    public ResponseEntity changeUsername(@PathVariable Integer id , @PathVariable String username){
        userService.changeUsername(id,username);
        return ResponseEntity.status(HttpStatus.OK).body("username changed");
    }







}
