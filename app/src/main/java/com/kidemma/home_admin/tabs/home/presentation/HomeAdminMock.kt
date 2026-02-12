package com.kidemma.home_admin.tabs.home.presentation

import com.kidemma.R
import com.kidemma.common.enums.MessageType
import com.kidemma.common.models.CardTextElementUiModel

/*
 * File: HomeAdminMock
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 24/02/26
 * Last modified: 09/03/26
 */

object HomeAdminMock {
    val classStatusList = listOf(
        CardTextElementUiModel(
            text = R.string.class_status_possibly_cancel,
            messageType = MessageType.DEFAULT,
            count = 1,
            iconID = R.drawable.ic_warning,
            iconDescription = R.string.testing_text),
        CardTextElementUiModel(
            text = R.string.class_status_definitely_cancel,
            messageType = MessageType.DEFAULT,
            count = 10,
            iconID = R.drawable.ic_danger,
            iconDescription = R.string.testing_text)
    )

    val paymentStatusList = listOf(
        CardTextElementUiModel(
            text = R.string.payment_status_pending,
            messageType = MessageType.INFO,
            count = 1,
            iconDescription = R.string.testing_text),
        CardTextElementUiModel(
            text = R.string.payment_status_expired,
            messageType = MessageType.ERROR,
            count = 1,
            iconDescription = R.string.testing_text),
        CardTextElementUiModel(
            text = R.string.payment_status_approvals,
            messageType = MessageType.WARNING,
            count = 1,
            iconDescription = R.string.testing_text)
    )

    val complaintsList = listOf(
        CardTextElementUiModel(
            text = R.string.complaints_pending,
            messageType = MessageType.INFO,
            count = 0,
            iconDescription = R.string.testing_text),
        CardTextElementUiModel(
            text = R.string.complaints_discarded,
            messageType = MessageType.ERROR,
            count = 0,
            iconDescription = R.string.testing_text)
    )
}