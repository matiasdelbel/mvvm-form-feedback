package com.fabrik.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fabrik.design.theme.Theme
import com.fabrik.form.review.ReviewRoute
import com.fabrik.form.review.review
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent { Theme { MainScreen() } }
    }
}

@Composable
private fun MainScreen(modifier: Modifier = Modifier) {
    val insets = WindowInsets.systemBars.asPaddingValues()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier.padding(insets),
        startDestination =  ReviewRoute
    ) {
        review(navController)
    }
}
