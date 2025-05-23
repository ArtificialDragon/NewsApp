package com.dragonlordian.newsapp.domain.use_cases.news

import androidx.paging.PagingData
import com.dragonlordian.newsapp.domain.model.Article
import com.dragonlordian.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }
}