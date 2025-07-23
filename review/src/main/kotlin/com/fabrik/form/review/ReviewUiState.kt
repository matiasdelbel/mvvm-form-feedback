package com.fabrik.form.review

data class ReviewUiState(
    val item: ReviewItemUiState,
    val review: ReviewRatingUiState? = null,
    val errors: ReviewError? = null,
)

data class ReviewItemUiState(
    val itemId: String,
    val itemName: String,
    val shortDescription: String,
    val imageUrl: String,
)

data class ReviewRatingUiState(
    val rating: Int = 0,
    val description: String = "",
    val maxRating: Int = 5,
    val synchronizing: Boolean = false,
)
