package com.dragonlordian.newsapp.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dragonlordian.newsapp.R
import com.dragonlordian.newsapp.presentation.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmark),
                    contentDescription = "Bookmark"
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Search"
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_network),
                    contentDescription = "Browse"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailsTopBarPreview() {
    NewsAppTheme {
        DetailsTopBar(
            onBrowsingClick = {},
            onShareClick = {},
            onBookmarkClick = {},
            onBackClick = {}
        )
    }

}