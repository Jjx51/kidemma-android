package com.kidemma.common.domain.models

/*
 * File: KidDetailCardUiModel.kt
 * Description: UI model for the kid detail card, including kid info and weekly schedule.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

data class KidDetailCardUiModel(
    val kidUiModel: KidUiModel,
    val weekScheduleUiModel : WeekScheduleUiModel
)
