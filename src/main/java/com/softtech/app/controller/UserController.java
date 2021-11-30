package com.softtech.app.controller;

import com.softtech.app.dto.JwtDto;
import com.softtech.app.dto.LoginRequestDto;
import com.softtech.app.model.StudentEntity;
import com.softtech.app.dto.UserDto;
import com.softtech.app.model.User;
import com.softtech.app.service.implemetation.StudentDoaService;
import com.softtech.app.service.interfaces.UserService;
import com.softtech.app.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by N.Bimeri on 20/08/2021.
 */
@RestController
@CrossOrigin
@RequestMapping(Constant.API_VERSION)
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StudentDoaService studentDoaService;

    @PostMapping(path = "public/login")
    public ResponseEntity<JwtDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) throws Exception{
        logger.info("login detail: "+ loginRequestDto);
        return ResponseEntity.ok(userService.loginUser(loginRequestDto));
    }

    @GetMapping(path = "public/users")
    public List<User> exampleUser(){
        return userService.getAllUers();
    }

    @PostMapping(path = "public/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto userDto){
//     @RequestHeader(name = "Accept-Language", required = false) Locale locale
//     return messageSource.getMessage("good.morning", null, "Default-message", locale);
        userService.addUser(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(location).build();
//        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "public/users/{id}")
    public UserDto getOneUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PutMapping(path = "public/users/{id}")
    public User updateUserInfo(@RequestBody UserDto userDto, @PathVariable Long id){
        logger.info("the id at the controller is: "+ id);
        return userService.updateUserInfo(userDto, id);
    }

    @DeleteMapping(path = "public/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "public/students")
    public ResponseEntity<StudentEntity> addStudent(@Valid @RequestBody StudentEntity studentEntity){

        return ResponseEntity.ok(studentDoaService.addStudent(studentEntity));
    }
}
