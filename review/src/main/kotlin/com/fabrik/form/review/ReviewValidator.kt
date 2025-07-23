package com.fabrik.form.review

class ReviewValidator {

    fun validate(review: ReviewRatingUiState): ReviewError = ReviewError(
        rating = validate(review.rating),
        description = validate(review.description),
    )

    fun validate(rating: Int): RatingError? = when (rating) {
        !in 1..5 -> RatingError
        else -> null
    }

    fun validate(description: String): DescriptionError? = when {
        description.length < 10 -> DescriptionError.DescriptionTooShort
        description.length > 1000 -> DescriptionError.DescriptionTooLong
        else -> null
    }
}
