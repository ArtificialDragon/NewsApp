package com.dragonlordian.newsapp.domain.use_cases.news

import androidx.paging.PagingData
import com.dragonlordian.newsapp.domain.model.Article
import com.dragonlordian.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}