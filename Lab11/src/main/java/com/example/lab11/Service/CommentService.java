package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {


    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }



    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    public List<Comment> getComments(){

        return commentRepository.findAll();
    }


    public void addComment(Comment comment){
        User user = userRepository.findUserByUser_id(comment.getPost_id());
        Post post = postRepository.findPostById(comment.getPost_id());

        if(user==null){
            throw new ApiException("author id not found");
        }

        if(post==null){

            throw new ApiException("post id not found");
        }

        commentRepository.save(comment);
    }


    public void updateComment(Integer id , Comment comment){

        Comment oldComment = commentRepository.findCommentById(id);
        User user = userRepository.findUserByUser_id(comment.getUser());
        Post post = postRepository.findPostById(comment.getPost_id());

        if(oldComment==null){

            throw  new ApiException("id not found");
        }
        if(user==null){
            throw new ApiException("author id not found");
        }

        if(post==null){

            throw new ApiException("post id not found");
        }

        oldComment.setComment_date(comment.getComment_date());
        oldComment.setContent(comment.getContent());
        oldComment.setUser(comment.getUser());
        oldComment.setPost_id(comment.getPost_id());
        commentRepository.save(oldComment);
    }


    public void deleteComment(Integer id){
        Comment oldComment = commentRepository.findCommentById(id);

        if(oldComment==null){

            throw  new ApiException("id not found");
        }

        commentRepository.delete(oldComment);

    }


    public List<Comment> getBeforeDate(Date comment_date){
        if(commentRepository.commentBeforeDate(comment_date).isEmpty()){
            throw new ApiException("no comment before this date");
        }

        return commentRepository.commentBeforeDate(comment_date);
    }


    public List<Comment> getByPost(Integer post_id){
        if(commentRepository.commentByPost(post_id).isEmpty()){
            throw new ApiException("no comments for this post");
        }

        return commentRepository.commentByPost(post_id);
    }

  }
