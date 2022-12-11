package com.harcyah.sample.spring.repository;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedNativeQuery(
    name = "Comment.findCommentDetails",
    query = "SELECT p.id AS postId, p.title AS postTitle, c.content as commentContent FROM post p JOIN comment c ON p.id = c.post_id ORDER BY postTitle, commentContent",
    resultSetMapping = "CommentDetailsMapping"
)
@SqlResultSetMapping(
    name = "CommentDetailsMapping",
    classes = @ConstructorResult(
        targetClass = CommentDetails.class,
        columns = {
            @ColumnResult(name = "postId", type = Long.class),
            @ColumnResult(name = "postTitle", type = String.class),
            @ColumnResult(name = "commentContent", type = String.class),
        }
    )
)
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence")
    @SequenceGenerator(name = "comment_id_sequence", sequenceName = "comment_id_sequence")
    private Long id;

    @ManyToOne
    private Post post;

    private String author;
    private String content;

}
