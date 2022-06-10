package com.Devmountain.groceryApp.dto;

import javax.persistence.*;

import com.Devmountain.groceryApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; //makes life easier you need to write out setters and getters

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String password;
    //private Set<NoteDto> noteSet = new HashSet<>(); don't need this yet

    public UserDto(User user) {
        if(user.getId() != null) {
            this.id = user.getId();
        }

        if(user.getUsername() != null) {
            this.username = user.getUsername();
        }

        if(user.getPassword() != null) {
            this.password = user.getPassword();
        }

    }
}