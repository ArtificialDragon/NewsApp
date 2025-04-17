package com.dragonlordian.newsapp.di

import android.app.Application
import androidx.room.Room
import com.dragonlordian.newsapp.data.local.NewsDao
import com.dragonlordian.newsapp.data.local.NewsDatabase
import com.dragonlordian.newsapp.data.local.NewsTypeConverter
import com.dragonlordian.newsapp.data.manager.LocalUserManagerImpl
import com.dragonlordian.newsapp.data.remote.NewsApi
import com.dragonlordian.newsapp.data.repository.NewsRepositoryImpl
import com.dragonlordian.newsapp.domain.manager.LocalUserManager
import com.dragonlordian.newsapp.domain.repository.NewsRepository
import com.dragonlordian.newsapp.domain.use_cases.app_entry.AppEntryUseCases
import com.dragonlordian.newsapp.domain.use_cases.app_entry.ReadAppEntry
import com.dragonlordian.newsapp.domain.use_cases.app_entry.SaveAppEntry
import com.dragonlordian.newsapp.domain.use_cases.news.DeleteArticle
import com.dragonlordian.newsapp.domain.use_cases.news.GetNews
import com.dragonlordian.newsapp.domain.use_cases.news.NewsUseCases
import com.dragonlordian.newsapp.domain.use_cases.news.SearchNews
import com.dragonlordian.newsapp.domain.use_cases.news.SelectArticle
import com.dragonlordian.newsapp.domain.use_cases.news.SelectArticles
import com.dragonlordian.newsapp.domain.use_cases.news.UpsertArticle
import com.dragonlordian.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            "news_db.db"
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        db: NewsDatabase
    ): NewsDao{
        return db.newsDao
    }

}