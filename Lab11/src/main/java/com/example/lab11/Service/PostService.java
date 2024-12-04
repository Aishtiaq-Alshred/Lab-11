package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class PostService {
    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public List<Post> getPosts(){

        return postRepository.findAll();
    }


    public void addPost(Post post){
        User user = userRepository.findUserByUser_id(post.getId());
        Category category = categoryRepository.findCategoriesById(post.getCategory_id());
        if(user==null){
            throw  new ApiException("author id not found");
        }
        if(category==null){
            throw  new ApiException("category id not found");
        }



        postRepository.save(post);
    }


    public void updatePost(Integer id , Post post){

        Post post1 = postRepository.findPostById(id);
        User user = userRepository.findUserByUser_id(post.getId());
        Category category = categoryRepository.findCategoriesById(post.getCategory_id());
        if(post1==null){
            throw new ApiException("id not found");

        }
        if(user==null){
            throw  new ApiException("author id not found");
        }
        if(category==null){
            throw  new ApiException("category id not found");
        }

        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setPublication_date(post.getPublication_date());

        postRepository.save(post1);
    }



    public void deletePost(Integer id){

        Post post1 = postRepository.findPostById(id);
        if(post1==null){
            throw new ApiException("id not found");
        }

        postRepository.delete(post1);

    }


    public List<Post> allPostBetweenDate(Date start_date , Date end_date){
        if(postRepository.PostBetweenDate(start_date, end_date).isEmpty()){
            throw new ApiException("no posts between these dates");

        }

        return postRepository.PostBetweenDate(start_date, end_date);
    }




    public List<Post> getByCategory(Integer id){

        if(postRepository.postByCategory(id).isEmpty()){

            throw new ApiException("no post in this category");
        }

        return postRepository.postByCategory(id);

    }


    public void EditPostContent(Integer id,String content){
        Post post = postRepository.findPostById(id);
        if(post==null){
            throw new ApiException("id not found");
        }

        post.setContent(content);
        postRepository.save(post);
    }


}
