package com.example.auth.Controllers;


import com.example.auth.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/list/{id}")
    @ResponseBody
    public Object list(@PathVariable("id") String id){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(){
        return userService.getAllUsers();
    }


}
