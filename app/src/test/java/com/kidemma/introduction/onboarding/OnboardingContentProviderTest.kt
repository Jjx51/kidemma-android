package com.kidemma.introduction.onboarding

import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.kidemma.introduction.onboarding.presentation.OnboardingContentProvider
import com.kidemma.R
import org.junit.Test

class OnboardingContentProviderTest {

    @Test
    fun getOnboardingPages_returnsCorrectNumberOfPages() {
        // --- WHEN ---
        // We call the method to get the onboarding pages.
        val pages = OnboardingContentProvider.getOnboardingPages()

        // --- THEN ---
        // Verify that the list contains exactly 3 pages.
        assertThat(pages).hasSize(3)
    }

    @Test
    fun getOnboardingPages_firstPageHasCorrectData() {
        // --- WHEN ---
        val firstPage = OnboardingContentProvider.getOnboardingPages().first()

        // --- THEN ---
        // Verify that the content of the first page is correct.
        assertThat(firstPage.imageUiModel.resId).isEqualTo(R.drawable.onboarding_image_1)
        Truth.assertThat(firstPage.imageUiModel.size).isEqualTo(200.dp)
        assertThat(firstPage.titleText).isEqualTo(R.string.onboarding_title_1)
        assertThat(firstPage.bodyText).isEqualTo(R.string.onboarding_body_1)
    }

    @Test
    fun getOnboardingPages_secondPageHasCorrectData() {
        // --- WHEN ---
        val secondPage =
            OnboardingContentProvider.getOnboardingPages()[1] // Get the second page by index

        // --- THEN ---
        // Verify that the content of the second page is correct.
        assertThat(secondPage.imageUiModel.resId).isEqualTo(R.drawable.onboarding_image_2)
        Truth.assertThat(secondPage.imageUiModel.size).isEqualTo(200.dp)
        assertThat(secondPage.titleText).isEqualTo(R.string.onboarding_title_2)
        assertThat(secondPage.bodyText).isEqualTo(R.string.onboarding_body_2)
    }

    @Test
    fun getOnboardingPages_thirdPageHasCorrectData() {
        // --- WHEN ---
        val thirdPage = OnboardingContentProvider.getOnboardingPages().last()

        // --- THEN ---
        // Verify that the content of the third page is correct.
        assertThat(thirdPage.imageUiModel.resId).isEqualTo(R.drawable.onboarding_image_3)
        Truth.assertThat(thirdPage.imageUiModel.size).isEqualTo(200.dp)
        assertThat(thirdPage.titleText).isEqualTo(R.string.onboarding_title_3)
        assertThat(thirdPage.bodyText).isEqualTo(R.string.onboarding_body_3)
    }
}