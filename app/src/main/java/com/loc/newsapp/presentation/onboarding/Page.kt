package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)

val pages = listOf(

    Page(
        title = "News",
        description = "We make sure to provide the most recent and important news from the best possible sources.",
        image = R.drawable.news_wallpaper2
    ),

    Page(
        title = "Search",
        description = "Feel comfortable searching for different topics that may concern you and match your interest.",
        image = R.drawable.search_wallpaper
    ),

    Page(
        title = "Bookmark",
        description = "Save your favourite articles so you can check it later or share it with family and friends.",
        image = R.drawable.bookmark_wallpaper
    )

)
