package com.kidemma.common.utils

import androidx.compose.ui.graphics.Color
import com.kidemma.common.enums.MessageType
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: DisplayUiColor
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

object DisplayUiColor {

    fun getDisplayTextColor(messageType: MessageType): Color {
        return when (messageType) {
            MessageType.INFO -> KidemmaColors.Info
            MessageType.ERROR -> KidemmaColors.Error
            MessageType.WARNING -> KidemmaColors.Warning
            MessageType.DEFAULT -> KidemmaColors.Text
        }
    }

}