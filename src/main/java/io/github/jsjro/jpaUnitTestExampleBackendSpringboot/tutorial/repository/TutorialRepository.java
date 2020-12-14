package io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.repository;

import io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}