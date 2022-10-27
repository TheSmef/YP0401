package com.example.YP.Repository;

import com.example.YP.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository
        extends CrudRepository<Post, Long> {
    public List<Post> findByName(String name);

    public List<Post> findByNameContaining(String name);
}
