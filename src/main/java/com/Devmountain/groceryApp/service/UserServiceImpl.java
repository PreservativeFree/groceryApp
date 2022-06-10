package com.Devmountain.groceryApp.service;

import com.Devmountain.groceryApp.dto.UserDto;
import com.Devmountain.groceryApp.model.User;
import com.Devmountain.groceryApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//I am implementing an interface but all work is all the work is done by another interface; UserRepository
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        User savedUser = userRepository.saveAndFlush(user);
        if(savedUser != null)
            response.add("http://localhost:8080/login.html");//response.add("User Added Successfully");
        else
            response.add("User Added command failed");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>(); //Array of Responses
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername()); //I need to avoid NULLPointerExceptions
        if(userOptional.isPresent()) {
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) { //code for Password check
                response.add("http://localhost:8080/index.html");//this takes me to index.html
                response.add(String.valueOf(userOptional.get().getId()));
            } else{
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}