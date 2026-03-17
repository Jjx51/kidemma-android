package com.kidemma.homeAdmin.tabs.agenda.presentation.models

import com.kidemma.common.ui.models.ImageUiModel
import java.time.LocalTime

/*
 * File: AgendaTabUiModels
 * Description: UI models for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 06/03/26
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
