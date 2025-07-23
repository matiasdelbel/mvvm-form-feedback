package com.fabrik.design.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StarButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        StarIcon(selected = selected)
    }
}

@Composable
@Preview
internal fun StartButtonPreview() {
    Row {
        StarButton(onClick = { }, selected = false)
        StarButton(onClick = { }, selected = true)
    }
}