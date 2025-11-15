package com.akaha.webblog.repository;

import com.akaha.webblog.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
