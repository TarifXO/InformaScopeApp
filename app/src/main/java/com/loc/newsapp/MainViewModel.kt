package com.loc.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.useCases.appEntry.AppEntryUseCases
import com.loc.newsapp.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel(){
    var splashCondition by mutableStateOf(true)
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
    init {
        appEntryUseCases.readAppEntry().onEach { startFromHomeScreen ->
            startDestination = if (startFromHomeScreen){
                Route.NewsNavigation.route
            }else{
                Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}