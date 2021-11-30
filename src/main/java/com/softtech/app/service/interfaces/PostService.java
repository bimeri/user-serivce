package com.softtech.app.service.interfaces;

import com.softtech.app.model.Post;

/**
 * Created by N.Bimeri on 26/08/2021.
 */
public interface PostService {
    Post addUserPost(Post post, Long id);
}
