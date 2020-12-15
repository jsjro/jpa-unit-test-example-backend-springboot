package io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.repository;

import io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStatus(boolean status);

    List<Post> findByTitleContaining(String title);
}