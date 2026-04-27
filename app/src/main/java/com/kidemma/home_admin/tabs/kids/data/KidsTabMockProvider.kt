package com.kidemma.home_admin.tabs.kids.data

import com.kidemma.common.domain.models.KidDetailCardUiModel
import com.kidemma.common.domain.models.KidUiModel
import com.kidemma.common.domain.models.WeekScheduleUiModel
import com.kidemma.common.utils.DayOfWeek
import com.kidemma.common.utils.Gender
import java.time.LocalDate

/*
 * File: KidsTabMockProvider.kt
 * Description: Provides mock data for Kids tab previews/tests.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 22/04/26
 */

object KidsTabMockProvider {
    fun getKidsDetailList(): List<KidDetailCardUiModel> {
        return listOf(
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "1",
                    name = "Alice Smith",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(3).minusMonths(6),
                    gender = Gender.FEMALE
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.WEDNESDAY to true,
                        DayOfWeek.FRIDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "2",
                    name = "Ben Johnson",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(4).minusMonths(1)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.TUESDAY to true,
                        DayOfWeek.THURSDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "3",
                    name = "Carla Martínez",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(2).minusMonths(3),
                    gender = Gender.FEMALE
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.TUESDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "4",
                    name = "Diego López",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(1)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.WEDNESDAY to true,
                        DayOfWeek.THURSDAY to true,
                        DayOfWeek.FRIDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "5",
                    name = "Emma Brown",
                    profileImage = null,
                    birthday = LocalDate.now().minusMonths(9),
                    gender = Gender.FEMALE
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.THURSDAY to true,
                        DayOfWeek.SATURDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "6",
                    name = "Felix García",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(4).minusMonths(11)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.FRIDAY to true,
                        DayOfWeek.SUNDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "7",
                    name = "Grace Lee",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(1).minusMonths(8),
                    gender = Gender.FEMALE
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.TUESDAY to true,
                        DayOfWeek.WEDNESDAY to true,
                        DayOfWeek.SUNDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "8",
                    name = "Hugo Pérez",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(4).minusMonths(4)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.TUESDAY to true,
                        DayOfWeek.WEDNESDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "9",
                    name = "Isabella Rossi",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(2).minusMonths(7),
                    gender = Gender.FEMALE
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.FRIDAY to true,
                        DayOfWeek.SATURDAY to true,
                        DayOfWeek.SUNDAY to true
                    )
                )
            ),
            KidDetailCardUiModel(
                kidUiModel = KidUiModel(
                    id = "10",
                    name = "Julián Torres",
                    profileImage = null,
                    birthday = LocalDate.now().minusYears(3).minusMonths(11)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.WEDNESDAY to true,
                        DayOfWeek.SATURDAY to true
                    )
                )
            )
        )
    }
}
