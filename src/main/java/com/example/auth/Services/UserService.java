package com.example.auth.Services;

import com.example.auth.Entities.UsersEntity;
import com.example.auth.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public List<UsersEntity> getAllUsers(){
        return usersRepository.findAll();
    }

}
