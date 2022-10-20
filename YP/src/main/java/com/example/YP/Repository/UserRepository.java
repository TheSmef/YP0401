package com.example.YP.Repository;

import com.example.YP.Models.Post;
import com.example.YP.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String name);
}
