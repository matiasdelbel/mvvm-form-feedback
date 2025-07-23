package com.fabrik.form.review.pane

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import com.fabrik.design.theme.spacers
import com.fabrik.form.review.R
import com.fabrik.form.review.ReviewItemUiState

@Composable
internal fun ReviewLandingPane(
    state: ReviewItemUiState,
    onStartReview: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.item_to_review),
            style = MaterialTheme.typography.titleMedium,
        )

        HorizontalDivider(Modifier.padding(vertical = MaterialTheme.spacers.sm))

        Row {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(state.imageUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacers.sm)
                    .size(140.dp)
            )

            Column {
                Text(
                    text = state.itemName,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.sm))

                Text(
                    text = state.shortDescription,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.lg))

                Button(
                    onClick = onStartReview,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(stringResource(R.string.start_review))
                }
            }
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalCoilApi::class)
private fun ReviewLandingPanePreview() {
    val imagePreviewHandler = AsyncImagePreviewHandler {
        ColorImage(color = Color.LightGray.toArgb())
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides imagePreviewHandler) {
        ReviewLandingPane(
            modifier = Modifier.padding(all = MaterialTheme.spacers.md),
            state = ReviewItemUiState(
                itemId = "",
                itemName = "Brand new California blue t-shirt",
                shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                imageUrl = "www.fabrik.com"
            ),
            onStartReview = { }
        )
    }
}
