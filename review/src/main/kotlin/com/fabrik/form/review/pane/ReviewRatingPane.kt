package com.fabrik.form.review.pane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fabrik.design.theme.spacers
import com.fabrik.form.review.DescriptionError
import com.fabrik.form.review.NoReviewError
import com.fabrik.form.review.R
import com.fabrik.form.review.ReviewError
import com.fabrik.form.review.ReviewRatingUiState
import com.fabrik.design.component.StarButton

@Composable
internal fun ReviewRatingPane(
    state: ReviewRatingUiState,
    onRatingSelected: (rating: Int) -> Unit,
    onDescriptionChanged: (description: String) -> Unit,
    onReviewFinished: () -> Unit,
    modifier: Modifier = Modifier,
    errors: ReviewError? = null,
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.how_many_stars_would_you_give),
            style = MaterialTheme.typography.titleMedium,
        )

        HorizontalDivider(Modifier.padding(vertical = MaterialTheme.spacers.sm))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            for (i in 1..state.maxRating) {
                StarButton(selected = state.rating >= i, onClick = { onRatingSelected(i) })
            }
        }
        if (errors?.rating != null) {
            Text(
                text = stringResource(R.string.please_rate_the_product),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.md))

        OutlinedTextField(
            value = state.description,
            onValueChange = onDescriptionChanged,
            label = { Text(stringResource(R.string.describe_your_experience)) },
            supportingText = if (errors?.description == null) { null } else {
                {
                    Text(
                        text = when (errors.description) {
                            DescriptionError.DescriptionTooLong -> stringResource(R.string.description_too_long)
                            DescriptionError.DescriptionTooShort -> stringResource(R.string.description_too_short)
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            minLines = 3,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.lg))

        Button(
            onClick = onReviewFinished,
            modifier = Modifier.align(Alignment.End),
            enabled = errors == NoReviewError
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview
@Composable
internal fun ReviewRatingPanePreview() {
    ReviewRatingPane(
        state = ReviewRatingUiState(rating = 3),
        errors = NoReviewError,
        onRatingSelected = { },
        onDescriptionChanged = { },
        onReviewFinished = { },
        modifier = Modifier.padding(all = MaterialTheme.spacers.md)
    )
}
