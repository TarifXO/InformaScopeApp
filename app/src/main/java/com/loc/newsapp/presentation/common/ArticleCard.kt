package com.loc.newsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.Dimens.articleCardSize
import com.loc.newsapp.presentation.Dimens.extraSmallPadding
import com.loc.newsapp.presentation.Dimens.extraSmallPadding2
import com.loc.newsapp.presentation.Dimens.smallIconSize
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    article: Article,
    onClick: (Article) -> Unit
) {
    val context = LocalContext.current

    Row(modifier = Modifier.clickable{ onClick(article) }){
        AsyncImage(modifier = Modifier
            .size(articleCardSize)
            .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build()
            , contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal = extraSmallPadding)
                .height(articleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                    )

                Spacer(modifier = Modifier.width(extraSmallPadding2))

                Icon(painter = painterResource(id = R.drawable.ic_time), contentDescription = null,
                    modifier = Modifier.size(smallIconSize),
                    tint = colorResource(id = R.color.body)
                    )

                Spacer(modifier = Modifier.width(extraSmallPadding2))
                
                Text(text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview(){
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Her triant broke down, Her phone died. and then she met her saver",
                url = "",
                urlToImage = "",
            ),
            onClick = {}
        )
    }
}