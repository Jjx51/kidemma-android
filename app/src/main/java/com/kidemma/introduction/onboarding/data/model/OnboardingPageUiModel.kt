package com.kidemma.introduction.onboarding.data.model

import androidx.annotation.StringRes
import com.kidemma.common.ui.models.ImageUiModel

/*
 * File: OnboardingPageUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class OnboardingPageUiModel(
    val imageUiModel: ImageUiModel,
    @param:StringRes val titleText: Int,
    @param:StringRes val bodyText: Int,
    @param:StringRes val buttonText: Int,
)
