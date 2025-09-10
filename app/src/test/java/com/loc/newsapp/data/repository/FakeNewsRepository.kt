package com.loc.newsapp.data.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsRepository: NewsRepository {

    private val articles: MutableList<Article> = mutableListOf()

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return flow { emit(PagingData.from(articles)) }
    }


    override fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertArticle(article: Article) {
        articles.add(article)
    }

    override suspend fun deleteArticle(article: Article) {
        articles.remove(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun selectArticle(url: String): Article? {
        TODO("Not yet implemented")
    }
}