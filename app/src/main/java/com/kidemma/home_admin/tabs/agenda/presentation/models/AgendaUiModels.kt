package com.kidemma.home_admin.tabs.agenda.presentation.models

import com.kidemma.common.ui.models.ImageUiModel
import java.time.LocalTime

/*
 * File: AgendaUiModels
 * Description: UI models for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
data class KidUiModel(
    val id: String,
    val name: String,
    val ageDescription: String,
    val image: ImageUiModel? = null
)

data class ClassUiModel(
    val id: String,
    val startTime: LocalTime,
    val timeDescription: String,
    val kids: List<KidUiModel>
)
