package com.gakulakov.jwttokens.controller.article

import com.gakulakov.jwttokens.model.Article
import com.gakulakov.jwttokens.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/article")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping
    fun listAll(): List<ArticleResponse> {
        return articleService
            .findAll()
            .map {
                it.toResponse()
            }
    }

    private fun Article.toResponse(): ArticleResponse = ArticleResponse(
        id = id,
        title = title,
        content = content,
    )
}