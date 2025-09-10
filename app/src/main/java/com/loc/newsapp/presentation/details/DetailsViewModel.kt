package com.loc.newsapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.manager.LocalUserManager
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val userManager: LocalUserManager
) : ViewModel() {

    private val _sideEffect = MutableStateFlow<String?>(null)
    val sideEffect: StateFlow<String?> = _sideEffect

    fun onEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    }else{
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                _sideEffect.value = null
            }

        }
    }

    fun isArticleBookmarked(articleUrl: String): Flow<Boolean> {
        return userManager.readIsBookmarked(articleUrl)
    }

    fun toggleBookmark(articleUrl: String) {
        viewModelScope.launch {
            val isBookmarked = userManager.readIsBookmarked(articleUrl).first()
            if (isBookmarked) {
                userManager.saveIsBookmarked(articleUrl, false)
            } else {
                userManager.saveIsBookmarked(articleUrl, true)
            }
        }
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        _sideEffect.value = "Article Saved"
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        _sideEffect.value = "Article Removed"
    }
}