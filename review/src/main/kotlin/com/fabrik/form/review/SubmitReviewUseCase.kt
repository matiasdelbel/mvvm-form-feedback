package com.fabrik.form.review

import kotlinx.coroutines.delay

interface SubmitReviewUseCase {

    suspend operator fun invoke() {
        delay(timeMillis = 2000)
    }
}
