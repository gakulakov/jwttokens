package com.gakulakov.jwttokens.service

import com.gakulakov.jwttokens.model.Article
import com.gakulakov.jwttokens.repository.ArticleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun findAll(): List<Article> = articleRepository.findALl()

    fun findById(id: UUID): Article? = articleRepository.findById(id)
}