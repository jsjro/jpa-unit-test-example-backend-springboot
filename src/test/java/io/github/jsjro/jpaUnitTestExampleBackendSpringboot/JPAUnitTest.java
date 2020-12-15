package io.github.jsjro.jpaUnitTestExampleBackendSpringboot;

import io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.model.Post;
import io.github.jsjro.jpaUnitTestExampleBackendSpringboot.tutorial.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void should_find_no_posts_if_repository_is_empty() {
        Iterable<Post> posts = postRepository.findAll();
        assertThat(posts).isEmpty();
    }

    @Test
    public void should_store_a_post() {
        Date newDate = new Date();

        Post post = postRepository.save(new Post("TEST_TITLE", "TEST_CONTENT", true, newDate, null));

        assertThat(post).hasFieldOrPropertyWithValue("title", "TEST_TITLE");
        assertThat(post).hasFieldOrPropertyWithValue("content", "TEST_CONTENT");
        assertThat(post).hasFieldOrPropertyWithValue("status", true);
        assertThat(post).hasFieldOrPropertyWithValue("create_date", newDate);
    }

    @Test
    public void should_find_all_posts() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        Iterable<Post> posts = postRepository.findAll();

        assertThat(posts).hasSize(3).contains(testPost1, testPost2, testPost3);
    }

    @Test
    public void should_find_post_by_id() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        Post foundTutorial = postRepository.findById(testPost2.getId()).get();

        assertThat(foundTutorial).isEqualTo(testPost2);
    }

    @Test
    public void should_find_status_posts() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        Iterable<Post> tutorials = postRepository.findByStatus(true);

        assertThat(tutorials).hasSize(2).contains(testPost1, testPost3);
    }

    @Test
    public void should_find_posts_by_title_containing_string() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1_DUMMY", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3_DUMMY", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        Iterable<Post> posts = postRepository.findByTitleContaining("_DUMMY");

        assertThat(posts).hasSize(2).contains(testPost1, testPost3);
    }

    @Test
    public void should_update_post_by_id() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        Date modifiedDate = new Date();
        Post updatedPost = new Post("TEST_TITLE_2_MODIFIED", "TEST_CONTENT_2_MODIFIED", true, newDate2, modifiedDate);

        Post findTestPost = postRepository.findById(testPost2.getId()).get();
        findTestPost.setTitle(updatedPost.getTitle());
        findTestPost.setContent(updatedPost.getContent());
        findTestPost.setStatus(updatedPost.isStatus());
        findTestPost.setCreate_date(updatedPost.getCreate_date());
        findTestPost.setModified_date(updatedPost.getModified_date());

        postRepository.save(findTestPost);

        Post checkTestPost = postRepository.findById(testPost2.getId()).get();

        assertThat(checkTestPost.getId()).isEqualTo(testPost2.getId());
        assertThat(checkTestPost.getTitle()).isEqualTo(updatedPost.getTitle());
        assertThat(checkTestPost.getContent()).isEqualTo(updatedPost.getContent());
        assertThat(checkTestPost.isStatus()).isEqualTo(updatedPost.isStatus());
        assertThat(checkTestPost.getCreate_date()).isEqualTo(updatedPost.getCreate_date());
        assertThat(checkTestPost.getModified_date()).isEqualTo(updatedPost.getModified_date());
    }

    @Test
    public void should_delete_post_by_id() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        postRepository.deleteById(testPost2.getId());

        Iterable<Post> tutorials = postRepository.findAll();

        assertThat(tutorials).hasSize(2).contains(testPost1, testPost3);
    }

    @Test
    public void should_delete_all_posts() {
        Date newDate1 = new Date();
        Post testPost1 = postRepository.save(new Post("TEST_TITLE_1", "TEST_CONTENT_1", true, newDate1, null));
        entityManager.persist(testPost1);

        Date newDate2 = new Date();
        Post testPost2 = postRepository.save(new Post("TEST_TITLE_2", "TEST_CONTENT_2", false, newDate2, null));
        entityManager.persist(testPost2);

        Date newDate3 = new Date();
        Post testPost3 = postRepository.save(new Post("TEST_TITLE_3", "TEST_CONTENT_3", true, newDate3, null));
        entityManager.persist(testPost3);

        postRepository.deleteAll();

        assertThat(postRepository.findAll()).isEmpty();
    }
}