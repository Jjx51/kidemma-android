package com.kidemma.home_admin.tabs.agenda.data

import com.kidemma.common.utils.Gender
import com.kidemma.home_admin.tabs.agenda.presentation.models.ClassUiModel
import com.kidemma.home_admin.tabs.agenda.presentation.models.KidUiModel
import java.time.LocalDate
import java.time.LocalTime

/*
 * File: AgendaTabMockProvider
 * Description: Mock data provider for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 28/05/26
 */
object AgendaTabMockProvider {

    private const val WEDNESDAY_VALUE = 3
    private const val SUNDAY_VALUE = 7
    private const val HOUR_9 = 9
    private const val HOUR_10 = 10
    private const val HOUR_11 = 11
    private const val MINUTE_0 = 0

    fun getClassesForDate(date: LocalDate): List<ClassUiModel> {
        // Only return classes for Mon-Sat as per requirements (except Wednesday for demo)
        if (date.dayOfWeek.value == SUNDAY_VALUE || date.dayOfWeek.value == WEDNESDAY_VALUE) return emptyList()

        return listOf(
            ClassUiModel(
                id = "1", startTime = LocalTime.of(HOUR_9, MINUTE_0), timeDescription = "9:00 am", kids = listOf(
                    KidUiModel("1", "Alejandro Ávila", "1 año 7 meses", gender = Gender.MALE),
                    KidUiModel("2", "Brenda Barrera", "1 año 5 meses", gender = Gender.FEMALE),
                    KidUiModel("3", "José Carrillo", "1 año 6 meses", gender = Gender.MALE),
                    KidUiModel("4", "Mateo Mendoza", "7 meses", gender = Gender.MALE)
                )
            ), ClassUiModel(
                id = "2", startTime = LocalTime.of(HOUR_10, MINUTE_0), timeDescription = "10:00 am", kids = listOf(
                    KidUiModel("5", "Luna Lunaria", "8 meses", gender = Gender.FEMALE), KidUiModel("6", "Santiago Cruz", "2 años", gender = Gender.MALE)
                )
            ), ClassUiModel(
                id = "3", startTime = LocalTime.of(HOUR_11, MINUTE_0), timeDescription = "11:00 am", kids = listOf(
                    KidUiModel("7", "Valeria Soto", "1 año 10 meses", gender = Gender.FEMALE)
                )
            )
        )
    }
}
