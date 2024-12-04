package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{

    Post findPostById(Integer id);


    @Query("select p from Post p where p.publication_date<?1 and  p.publication_date>?2")
    List<Post> PostBetweenDate(Date start_date , Date end_date);


    @Query("select p from Post p where p.category_id=?1")
    List<Post> postByCategory(Integer category_id);
}
