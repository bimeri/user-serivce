package com.softtech.app.service.implemetation;

import com.softtech.app.exceptions.UserNotFoundException;
import com.softtech.app.model.Post;
import com.softtech.app.model.User;
import com.softtech.app.repository.PostRepository;
import com.softtech.app.repository.UserRepository;
import com.softtech.app.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by N.Bimeri on 26/08/2021.
 */
@Service
public class PostImpl implements PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public Post addUserPost(Post post, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("there is no user with id: "+ id);
        post.setUser(user.get());
        return postRepository.save(post);
    }
}
