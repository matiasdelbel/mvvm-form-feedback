package com.fabrik.form.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewValidator: ReviewValidator,
    private val submitReviewUseCase: SubmitReviewUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(value = ReviewUiState(item = ReviewItemMockUiState))
    val state: StateFlow<ReviewUiState> = _state.asStateFlow()

    fun onRatingSelected(rating: Int) {
        val newReview = state.value.review?.copy(rating = rating) ?: ReviewRatingUiState(rating = rating)

        _state.value = state.value.copy(
            errors = reviewValidator.validate(review = newReview),
            review = newReview
        )
    }

    fun onDescriptionChanged(description: String) {
        val newReview = state.value.review?.copy(description = description) ?: ReviewRatingUiState(description = description)

        _state.value = state.value.copy(
            errors = reviewValidator.validate(review = newReview),
            review = newReview
        )
    }

    fun submitReview() {
        viewModelScope.launch(Dispatchers.IO) {
            val newReview = state.value.review?.copy(synchronizing = true) ?: ReviewRatingUiState(synchronizing = true)
            _state.value = state.value.copy(review = newReview)
            submitReviewUseCase()
            _state.value = state.value.copy(review = newReview.copy(synchronizing = false))
        }
    }
}

private val ReviewItemMockUiState = ReviewItemUiState(
    itemId = "3489",
    itemName = "Cortego City Damesfiets Plus 28 Inch - 7 Versnellingen",
    shortDescription = "Vanaf het moment dat je op de Cortego City damesfiets in mat-zwart stapt, voel je het verschil. Het verstelbare stuur en zadel zorgen voor een ergonomische zithouding.",
    imageUrl = "https://www.fietsenplaats.nl/cdn/shop/files/Cortego-damesfiets-zwart-plus-1.jpg"
)
