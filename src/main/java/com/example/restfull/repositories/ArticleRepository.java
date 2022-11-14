package com.example.restfull.repositories;

import com.example.restfull.models.Article;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByTitleContainingOrContentContaining(String text, String textAgain);
}
