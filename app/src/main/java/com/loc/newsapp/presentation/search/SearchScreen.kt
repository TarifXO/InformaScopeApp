package com.loc.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails : (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = mediumPadding1, start = mediumPadding1, end = mediumPadding1)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        SearchBar(text = state.searchQuery, readOnly = false, onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
            )

        Spacer(modifier = Modifier.height(mediumPadding1))

        state.articles?.let { it ->
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigateToDetails(it)})
        }
    }
}
