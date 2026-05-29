package com.kidemma.home_admin.tabs.agenda.presentation.models

import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.utils.Gender
import java.time.LocalTime

/*
 * File: AgendaTabUiModels
 * Description: UI models for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 28/05/26
 */

data class KidUiModel(
    val id: String,
    val name: String,
    val ageDescription: String,
    val image: ImageUiModel? = null,
    val gender: Gender
)

data class ClassUiModel(
    val id: String,
    val startTime: LocalTime,
    val timeDescription: String,
    val kids: List<KidUiModel>
)
