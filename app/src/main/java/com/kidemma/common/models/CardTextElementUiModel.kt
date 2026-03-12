package com.kidemma.common.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.kidemma.common.enums.MessageType
import com.kidemma.common.utils.DisplayUiColor

/*
 * File: CardTextElementUiModel
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 24/02/26
 * Last modified: 26/02/26
 */

data class CardTextElementUiModel(
    @param:StringRes val text: Int,
    val count: Int,
    val messageType: MessageType,
    @param:DrawableRes val iconID: Int? = null,
    @param:StringRes val iconDescription: Int
) {
    val displayColor : Color = DisplayUiColor.getDisplayTextColor(messageType = messageType)

}
