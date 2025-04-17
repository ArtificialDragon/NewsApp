package com.dragonlordian.newsapp.presentation.bookmark

import com.dragonlordian.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
