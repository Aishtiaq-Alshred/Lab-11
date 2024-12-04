package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {


    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public void addUser(User user){

        userRepository.save(user);
    }


    public void updateUser(Integer user_id,User user){
        User oldUser = userRepository.findUserByUser_id(user_id);

        if(oldUser==null){
            throw new ApiException("id not found");
        }

        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        oldUser.setRegisration_date(user.getRegisration_date());

        userRepository.save(oldUser);
    }


    public void deleteUser(Integer user_id){
        User oldUser = userRepository.findUserByUser_id(user_id);

        if(oldUser==null){
            throw new ApiException("id not found");
        }

        userRepository.delete(oldUser);
    }



    public void checkUser(String username , String password){

        User user = userRepository.findUserByUsernameAndPassword(username, password);

        if(user==null){

            throw new ApiException("wrong username or password");
        }


    }


    public void changeUsername(Integer id , String username){
        User oldUser = userRepository.findUserByUser_id(id);
        if(oldUser==null){
            throw new ApiException("id not found");
        }

        oldUser.setUsername(username);
        userRepository.save(oldUser);
    }
}
