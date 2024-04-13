package com.loc.newsapp.presentation.navigation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.presentation.Dimens.IconSize
import com.loc.newsapp.presentation.Dimens.extraSmallPadding2
import com.loc.newsapp.R
import com.loc.newsapp.ui.theme.NewsAppTheme


@Composable
fun NewsBottomNavigation(
    items : List<BottomNavigationItem>,
    selected : Int,
    onItemClick : (Int) -> Unit
) {
   NavigationBar(
       modifier = Modifier
           .fillMaxWidth(),
       containerColor = MaterialTheme.colorScheme.background,
       tonalElevation = 10.dp
   ) {
       items.forEachIndexed { index, item ->
           val isSelected = index == selected
           val iconRes = if (isSelected) item.selectedIcon else item.icon
           NavigationBarItem(
               selected = index == selected,
               onClick = { onItemClick(index) },
               icon = {
                   Column(horizontalAlignment = CenterHorizontally) {
                       Icon(
                           painter = painterResource(id = iconRes),
                           contentDescription = null,
                           modifier = Modifier.size(IconSize)
                       )

                       Spacer(modifier = Modifier.height(extraSmallPadding2))

                       Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                   }
               },
               colors = NavigationBarItemDefaults.colors(
                   selectedIconColor = MaterialTheme.colorScheme.primary,
                   selectedTextColor = MaterialTheme.colorScheme.primary,
                   unselectedIconColor = colorResource(id = R.color.body),
                   unselectedTextColor = colorResource(id = R.color.body),
                   indicatorColor = MaterialTheme.colorScheme.background
               )
           )
       }
   }
}

data class BottomNavigationItem(
    @DrawableRes val icon : Int,
    @DrawableRes val selectedIcon : Int,
    val text : String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview(){
    NewsAppTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            NewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(
                        icon = R.drawable.home,
                        selectedIcon = R.drawable.home_filled,
                        text = "Home"),
                    BottomNavigationItem(
                        icon = R.drawable.search,
                        selectedIcon = R.drawable.search_filled,
                        text = "Search"
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.bookmark_main_icon,
                        R.drawable.bookmark_main_icon_filled,
                        text = "Bookmark"
                    )
                ),
                selected = 0,
                onItemClick = {})
        }
    }
}