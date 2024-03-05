package com.kiparo.newsapp.presentation.features.articles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kiparo.newsapp.R
import com.kiparo.newsapp.core_ui.theme.articleImageSize
import com.kiparo.newsapp.core_ui.theme.heightArticleItem
import com.kiparo.newsapp.core_ui.theme.padding_6
import com.kiparo.newsapp.core_ui.theme.round_16
import com.kiparo.newsapp.core_ui.theme.round_8
import com.kiparo.newsapp.core_ui.theme.space_16
import com.kiparo.newsapp.core_ui.theme.space_4
import com.kiparo.newsapp.domain.models.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(article: Article, modifier: Modifier = Modifier) {
    Row(modifier = modifier.height(heightArticleItem)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(round_16))
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding_6)
        ) {
            GlideImage(
                modifier = Modifier
                    .size(articleImageSize)
                    .clip(RoundedCornerShape(round_8)),
                model = article.imageUrl,
                contentDescription = "Article image",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(space_16))
        Column {
            Text(
                text = stringResource(id = R.string.news_section, article.section),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(space_4))
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(space_4))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(space_4))
                Text(
                    text = "2th May",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(space_16))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "Time icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(space_4))
                Text(
                    text = "12:30 pm",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(space_16))
            }
        }
    }
}