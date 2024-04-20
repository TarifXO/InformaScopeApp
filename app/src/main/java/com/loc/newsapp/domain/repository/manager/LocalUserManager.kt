package com.loc.newsapp.domain.repository.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry ()
    fun readAppEntry() : Flow<Boolean>

    //////////////

    suspend fun saveIsBookmarked(articleUrl: String, isBookmarked: Boolean)
    fun readIsBookmarked(articleUrl : String): Flow<Boolean>
}