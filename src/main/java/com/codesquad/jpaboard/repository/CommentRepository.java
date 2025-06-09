package com.codesquad.jpaboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codesquad.jpaboard.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPostId(Long postId);
}
