package com.example.restfull.controller;

import com.example.restfull.exception.ArticleNotFoundException;
import com.example.restfull.models.Article;
import com.example.restfull.models.Like;
import com.example.restfull.repositories.ArticleRepository;
import com.example.restfull.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleLikeController {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/article/{articleId}/like")
    List<Like> getArticleLikes(@PathVariable Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));
        return likeRepository.findAll().stream().filter(a -> a.getArticle().equals(article)).collect(Collectors.toList());
    }

    @PostMapping("/article/{articleId}/like")
    Like createArticleLike(@RequestBody Like newLike, @PathVariable Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        newLike.setArticle(article);
        return likeRepository.save(newLike);
    }
}
