package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get")
    public ResponseEntity getPosts(){

        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.OK).body("post added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@Valid@RequestBody Post post , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(HttpStatus.OK).body("post update");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("post delete");
    }


    @GetMapping("/betweenDate/{start_date}/{end_date}")
    public ResponseEntity allPostBetweenDate(@PathVariable@DateTimeFormat(pattern ="yyyy-mm-dd") Date start_date, @PathVariable@DateTimeFormat(pattern ="yyyy-mm-dd")Date end_date){

        return ResponseEntity.status(HttpStatus.OK).body(postService.allPostBetweenDate(start_date, end_date));

    }


    @GetMapping("/getByCategory/{category_id}")
    public ResponseEntity getByCategory(@PathVariable Integer category_id){

        return ResponseEntity.status(HttpStatus.OK).body(postService.getByCategory(category_id));
    }


    @PutMapping("/edite/{id}/{comment}")
    public ResponseEntity EditePostContent(@PathVariable Integer id ,@PathVariable String comment){

        postService.EditPostContent(id, comment);
        return ResponseEntity.status(HttpStatus.OK).body("content edited");
    }

}
