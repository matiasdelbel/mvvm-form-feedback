package com.fabrik.form.review.pane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabrik.design.theme.spacers
import com.fabrik.form.review.R
import com.fabrik.form.review.ReviewRatingUiState
import com.fabrik.design.component.StarIcon

@Composable
internal fun ReviewSummaryPane(
    state: ReviewRatingUiState,
    onSubmitReview: () -> Unit,
    onChangeReview: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.this_is_what_your_review_looks_like),
            style = MaterialTheme.typography.titleMedium,
        )

        HorizontalDivider(Modifier.padding(vertical = MaterialTheme.spacers.sm))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            for (i in 1..state.maxRating) {
                StarIcon(
                    selected = state.rating >= i,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacers.sm)
                )
            }
        }

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.md))

        Text(
            text = "\"${state.description}\"",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.lg))

        Row(
            modifier = Modifier.align(Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                onClick = onChangeReview,
                enabled = !state.synchronizing
            ) {
                Text(stringResource(R.string.change_review))
            }
            Button(
                onClick = onSubmitReview,
                enabled = !state.synchronizing
            ) {
                Text(stringResource(R.string.submit))
            }

            if (state.synchronizing) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacers.xs)
                        .size(28.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewSummaryPanePreview() {
    ReviewSummaryPane(
        state = ReviewRatingUiState(
            rating = 4,
            description = "Here goes a small review of the product that we have just bought: The product works as expected, and the customer service is very helpful."
        ),
        modifier = Modifier.padding(all = MaterialTheme.spacers.md),
        onSubmitReview = { },
        onChangeReview = { },
    )
}
