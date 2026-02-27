package com.kidemma.common.ui.models

import androidx.annotation.StringRes
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.common.ui.models.icons.TrailingIconUiModel

/*
 * File: OutlinedTextFieldUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class OutlinedTextFieldUiModel(

    @param:StringRes val label: Int,
    @param:StringRes val placeholder: Int? = null,
    @param:StringRes val supportingText: Int? = null,

    val leadingIcon: IconUiModel? = null,
    val trailingIcon: TrailingIconUiModel? = null

)