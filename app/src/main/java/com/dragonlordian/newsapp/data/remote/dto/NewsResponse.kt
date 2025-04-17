package com.dragonlordian.newsapp.data.remote.dto

import com.dragonlordian.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)