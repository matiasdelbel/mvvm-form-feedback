package com.fabrik.form.review

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.fabrik.design.theme.spacers
import com.fabrik.form.review.pane.ReviewLandingPane
import com.fabrik.form.review.pane.ReviewRatingPane
import com.fabrik.form.review.pane.ReviewSummaryPane

fun NavGraphBuilder.review(navController: NavController) {
    navigation(
        route = ReviewRoute,
        startDestination = ReviewLandingRoute
    ) {
        reviewLanding(navController)
        reviewRating(navController)
        reviewSummary(navController)
    }
}

internal fun NavGraphBuilder.reviewLanding(navController: NavController) {
    composable(ReviewLandingRoute) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = ReviewRoute) }
        val viewModel = hiltViewModel<ReviewViewModel>(parentEntry)
        val state = viewModel.state.collectAsState().value

        ReviewLandingPane(
            state = state.item,
            onStartReview = { navController.navigate(ReviewRatingRoute) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacers.md)
        )
    }
}

internal fun NavGraphBuilder.reviewRating(navController: NavController) {
    composable(ReviewRatingRoute) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = ReviewRoute) }
        val viewModel = hiltViewModel<ReviewViewModel>(parentEntry)
        val state = viewModel.state.collectAsState().value

        ReviewRatingPane(
            state = state.review ?: ReviewRatingUiState(),
            errors = state.errors,
            onRatingSelected = { rating -> viewModel.onRatingSelected(rating) },
            onDescriptionChanged = { description -> viewModel.onDescriptionChanged(description) },
            onReviewFinished = { navController.navigate(ReviewSummaryRoute) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacers.md)
        )
    }
}

internal fun NavGraphBuilder.reviewSummary(navController: NavController) {
    composable(ReviewSummaryRoute) { entry ->
        val parentEntry = remember(entry) { navController.getBackStackEntry(route = ReviewRoute) }
        val viewModel = hiltViewModel<ReviewViewModel>(parentEntry)
        val state = viewModel.state.collectAsState().value

        ReviewSummaryPane(
            state = state.review ?: ReviewRatingUiState(),
            onChangeReview = { navController.popBackStack() },
            onSubmitReview = { viewModel.submitReview() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacers.md)
        )
    }
}

const val ReviewRoute = "review"
private const val ReviewLandingRoute = "review/landing"
private const val ReviewRatingRoute = "review/rating"
private const val ReviewSummaryRoute = "review/summary"
