package com.kidemma.introduction.onboarding.presentation

import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.introduction.onboarding.data.model.OnboardingPageUiModel

/*
 * File: OnboardingContentProvider
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
object OnboardingContentProvider {

    private val IMAGE_ONBOARDING_SIZE = 200.dp

    fun getOnboardingPages(): List<OnboardingPageUiModel> {
        return listOf(
            getOnboardingPage1(),
            getOnboardingPage2(),
            getOnboardingPage3()
        )
    }

    private fun getOnboardingPage1(): OnboardingPageUiModel {
        return OnboardingPageUiModel(
            imageUiModel = ImageUiModel(
                resId = R.drawable.onboarding_image_1,
                contentDescription = R.string.onboarding_image_content_description_1,
                size = IMAGE_ONBOARDING_SIZE
            ),
            titleText = R.string.onboarding_title_1,
            bodyText = R.string.onboarding_body_1,
            buttonText = R.string.onboarding_button_text_1
        )
    }

    private fun getOnboardingPage2(): OnboardingPageUiModel {
        return OnboardingPageUiModel(
            imageUiModel = ImageUiModel(
                resId = R.drawable.onboarding_image_2,
                contentDescription = R.string.onboarding_image_content_description_2,
                size = IMAGE_ONBOARDING_SIZE
            ),
            titleText = R.string.onboarding_title_2,
            bodyText = R.string.onboarding_body_2,
            buttonText = R.string.onboarding_button_text_2
        )
    }

    private fun getOnboardingPage3(): OnboardingPageUiModel {
        return OnboardingPageUiModel(
            imageUiModel = ImageUiModel(
                resId = R.drawable.onboarding_image_3,
                contentDescription = R.string.onboarding_image_content_description_3,
                size = IMAGE_ONBOARDING_SIZE
            ),
            titleText = R.string.onboarding_title_3,
            bodyText = R.string.onboarding_body_3,
            buttonText = R.string.onboarding_button_text_3
        )
    }
}