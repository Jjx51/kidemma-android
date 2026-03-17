package com.kidemma.homeAdmin.tabs.agenda.data

import com.kidemma.homeAdmin.tabs.agenda.presentation.models.ClassUiModel
import com.kidemma.homeAdmin.tabs.agenda.presentation.models.KidUiModel
import java.time.LocalDate
import java.time.LocalTime

/*
 * File: AgendaTabMockProvider
 * Description: Mock data provider for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
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
                id = "1",
                startTime = LocalTime.of(HOUR_9, MINUTE_0),
                timeDescription = "9:00 am",
                kids = listOf(
                    KidUiModel("1", "Alejandro Ávila", "1 año 7 meses"),
                    KidUiModel("2", "Brenda Barrera", "1 año 5 meses"),
                    KidUiModel("3", "José Carrillo", "1 año 6 meses"),
                    KidUiModel("4", "Mateo Mendoza", "7 meses")
                )
            ),
            ClassUiModel(
                id = "2",
                startTime = LocalTime.of(HOUR_10, MINUTE_0),
                timeDescription = "10:00 am",
                kids = listOf(
                    KidUiModel("5", "Luna Lunaria", "8 meses"),
                    KidUiModel("6", "Santiago Cruz", "2 años")
                )
            ),
            ClassUiModel(
                id = "3",
                startTime = LocalTime.of(HOUR_11, MINUTE_0),
                timeDescription = "11:00 am",
                kids = listOf(
                    KidUiModel("7", "Valeria Soto", "1 año 10 meses")
                )
            )
        )
    }
}
