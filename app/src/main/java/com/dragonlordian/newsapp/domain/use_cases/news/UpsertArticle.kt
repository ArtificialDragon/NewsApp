package com.dragonlordian.newsapp.domain.use_cases.news

import com.dragonlordian.newsapp.data.local.NewsDao
import com.dragonlordian.newsapp.domain.model.Article
import com.dragonlordian.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}