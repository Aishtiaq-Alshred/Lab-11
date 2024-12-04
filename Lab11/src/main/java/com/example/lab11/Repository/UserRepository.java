package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Integer>  {

    @Query("select u from  User u where u.user_id=?1")
    User findUserByUser_id(Integer User_id);
    User findUserByUsernameAndPassword(String username , String password);


}
