package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    @Query("select c from Comment c where c.comment_date>?1")
    List<Comment> commentBeforeDate(Date comment_date);
    @Query("select c from Comment c where c.post_id=?1")
    List<Comment> commentByPost(Integer post_id);

}
