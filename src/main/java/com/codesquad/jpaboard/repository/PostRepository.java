package com.codesquad.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codesquad.jpaboard.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
