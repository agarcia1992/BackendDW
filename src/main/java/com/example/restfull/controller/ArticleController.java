package com.example.restfull.controller;

import com.example.restfull.exception.ArticleNotFoundException;
import com.example.restfull.models.Article;
import com.example.restfull.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> all() {
        return articleRepository.findAll();
    }

    @PostMapping("/articles/search")
    public List<Article> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return articleRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/articles")
    public Article create(@Validated @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @GetMapping("/articles/{id}")
    Article show(@PathVariable Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @PutMapping("/articles/{id}")
    Article replaceArticle(@RequestBody Article newArticle, @PathVariable Integer id) {

        return articleRepository.findById(id)
            .map(article -> {
                article.setTitle(newArticle.getTitle());
                article.setContent(newArticle.getContent());
                return articleRepository.save(article);
            })
            .orElseGet(() -> {
                newArticle.setId(id);
                return articleRepository.save(newArticle);
            });
    }

    @DeleteMapping("/articles/{id}")
    void deleteArticle(@PathVariable Integer id) {
        articleRepository.deleteById(id);
    }
}
