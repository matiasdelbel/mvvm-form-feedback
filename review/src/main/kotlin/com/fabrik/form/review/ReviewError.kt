package com.fabrik.form.review

data class ReviewError(
    val rating: RatingError? = null,
    val description: DescriptionError? = null,
)

object RatingError

sealed interface DescriptionError {
    object DescriptionTooShort : DescriptionError
    object DescriptionTooLong : DescriptionError
}

val NoReviewError = ReviewError()
