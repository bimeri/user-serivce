package com.softtech.app.repository;

        import com.softtech.app.model.Post;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

/**
 * Created by N.Bimeri on 26/08/2021.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
