package com.loc.newsapp.presentation.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import com.loc.newsapp.R
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles : LazyPagingItems<Article>,
    navigateToSearch : () -> Unit,
    navigateToDetails : (Article) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 "){it.title}
            }else {
                ""
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = mediumPadding1)
        .statusBarsPadding()
    ) {
        Image(painter = painterResource(id = R.drawable.informa_scope_header), contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .padding(horizontal = mediumPadding1)
        )

        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                      navigateToSearch()
            },
            onSearch = {},
            modifier = Modifier
                .padding(mediumPadding1)
        )

        Spacer(modifier = Modifier.padding(start = mediumPadding1, end = mediumPadding1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = mediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        ArticlesList(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}