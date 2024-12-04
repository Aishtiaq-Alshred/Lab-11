package com.example.lab11.Controller;


import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/Comment")
public class CommentController {


  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }


  @GetMapping("/get")
  public ResponseEntity getComments(){

    return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments());
  }

  @PostMapping("/add")
  public ResponseEntity addComment(@Valid @RequestBody Comment comment , Errors errors){
    if(errors.hasErrors()){
      String message = errors.getFieldError().getDefaultMessage();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    commentService.addComment(comment);
    return ResponseEntity.status(HttpStatus.OK).body("comment added");

  }


  @PutMapping("/update/{id}")
  public ResponseEntity updateComment(@PathVariable Integer id ,@Valid @RequestBody Comment comment , Errors errors){
    if(errors.hasErrors()){
      String message = errors.getFieldError().getDefaultMessage();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    commentService.updateComment(id, comment);
    return ResponseEntity.status(HttpStatus.OK).body("comment updated");
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity deleteComment(@PathVariable Integer id){

    commentService.deleteComment(id);
    return ResponseEntity.status(HttpStatus.OK).body("comment deleted");
  }


  @GetMapping("/getBeforeDate/{comment_date}")
  public ResponseEntity getBeforeDate(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date comment_date){

    return ResponseEntity.status(HttpStatus.OK).body(commentService.getBeforeDate(comment_date));
  }


  @GetMapping("/getByPost/{post_id}")
  public ResponseEntity getCommentByPost(@PathVariable Integer post_id){

    return ResponseEntity.status(HttpStatus.OK).body(commentService.getByPost(post_id));
  }

  }
