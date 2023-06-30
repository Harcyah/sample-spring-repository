package com.harcyah.sample.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.harcyah.sample.spring.repository.Assertions.assertThat;

@SpringBootTest
class SampleSpringRepositoryIntegrationTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testRepositoriesAreDefined() {
        assertThat(commentRepository.count()).isEqualTo(0L);
        assertThat(postRepository.count()).isEqualTo(0L);
    }

    @Test
    void testFindCommentDetails() {
        Post post0 = new Post();
        post0.setAuthor("Author0");
        post0.setTitle("Title0");
        post0.setContent("Content0");

        Comment comment00 = new Comment();
        comment00.setPost(post0);
        comment00.setAuthor("Author00");
        comment00.setContent("CommentContent00");

        Comment comment01 = new Comment();
        comment01.setPost(post0);
        comment01.setAuthor("Author01");
        comment01.setContent("CommentContent01");

        Post post1 = new Post();
        post1.setAuthor("Author1");
        post1.setTitle("Title1");
        post1.setContent("Content1");

        Comment comment10 = new Comment();
        comment10.setPost(post1);
        comment10.setAuthor("Author10");
        comment10.setContent("CommentContent10");

        post0.setComments(List.of(comment00, comment01));
        post1.setComments(List.of(comment10));

        postRepository.save(post0);
        postRepository.save(post1);

        assertThat(postRepository.count()).isEqualTo(2L);
        assertThat(commentRepository.count()).isEqualTo(3L);

        List<CommentDetails> details = commentRepository.findCommentDetails();
        assertThat(details).hasSize(3);

        assertThat(details.get(0))
            .hasPostTitle("Title0")
            .hasCommentContent("CommentContent00");

        assertThat(details.get(1))
            .hasPostTitle("Title0")
            .hasCommentContent("CommentContent01");

        assertThat(details.get(2))
            .hasPostTitle("Title1")
            .hasCommentContent("CommentContent10");
    }

}
