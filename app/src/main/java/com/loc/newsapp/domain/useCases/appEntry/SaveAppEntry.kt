package com.loc.newsapp.domain.useCases.appEntry

import com.loc.newsapp.domain.repository.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}