package com.softtech.app.controller;

import com.softtech.app.exceptions.UserNotFoundException;
import com.softtech.app.model.Post;
import com.softtech.app.model.User;
import com.softtech.app.repository.UserRepository;
import com.softtech.app.service.interfaces.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by N.Bimeri on 26/08/2021.
 */
@RestController
@RequestMapping("api/public")
public class PostController {
    Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Object> userPosts(@RequestBody Post post, @PathVariable Long id){
         postService.addUserPost(post, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
         return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "users/{id}/posts")
    public List<Post> getUserPost(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException(messageSource.getMessage("no.user.id", null, LocaleContextHolder.getLocale()) + " " + id);
        List<Post> posts = user.get().getPosts();
        return posts;
    }
}
