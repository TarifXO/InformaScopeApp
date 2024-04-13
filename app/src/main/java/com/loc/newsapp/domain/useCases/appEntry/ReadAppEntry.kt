package com.loc.newsapp.domain.useCases.appEntry

import com.loc.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
     operator fun invoke() : Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}