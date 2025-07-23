package com.fabrik.design.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fabrik.design.R

@Composable
fun StarIcon(
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = when (selected) {
            true -> Icons.Default.Star
            false -> Icons.Outlined.Star
        },
        contentDescription = when (selected) {
            true -> stringResource(R.string.content_description_selected_star)
            false -> stringResource(R.string.content_description_unselected_star)
        },
        tint = when (selected) {
            true -> MaterialTheme.colorScheme.secondary
            false -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
        }
    )
}

@Composable
@Preview
internal fun StarIconPreview() {
    Row {
        StarIcon(selected = false)
        StarIcon(selected = true)
    }
}
