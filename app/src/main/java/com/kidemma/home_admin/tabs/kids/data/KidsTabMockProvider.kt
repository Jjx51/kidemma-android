package com.kidemma.home_admin.tabs.kids.data

import com.kidemma.common.domain.models.KidDetailCardUiModel
import com.kidemma.common.domain.models.KidUiModel
import com.kidemma.common.domain.models.WeekScheduleUiModel
import com.kidemma.common.utils.Gender
import java.time.LocalDate

/*
 * File: KidsTabMockProvider.kt
 * Description: Provides mock data for Kids tab previews/tests.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
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
                    hasClassOnMonday = true,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = true,
                    hasClassOnThursday = false,
                    hasClassOnFriday = true,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = false,
                    hasClassOnTuesday = true,
                    hasClassOnWednesday = false,
                    hasClassOnThursday = true,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = true,
                    hasClassOnTuesday = true,
                    hasClassOnWednesday = false,
                    hasClassOnThursday = false,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = false,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = true,
                    hasClassOnThursday = true,
                    hasClassOnFriday = true,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = false,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = false,
                    hasClassOnThursday = true,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = true,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = true,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = false,
                    hasClassOnThursday = false,
                    hasClassOnFriday = true,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = true
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
                    hasClassOnMonday = false,
                    hasClassOnTuesday = true,
                    hasClassOnWednesday = true,
                    hasClassOnThursday = false,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = true
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
                    hasClassOnMonday = true,
                    hasClassOnTuesday = true,
                    hasClassOnWednesday = true,
                    hasClassOnThursday = false,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = false,
                    hasClassOnSunday = false
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
                    hasClassOnMonday = false,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = false,
                    hasClassOnThursday = false,
                    hasClassOnFriday = true,
                    hasClassOnSaturday = true,
                    hasClassOnSunday = true
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
                    hasClassOnMonday = true,
                    hasClassOnTuesday = false,
                    hasClassOnWednesday = true,
                    hasClassOnThursday = false,
                    hasClassOnFriday = false,
                    hasClassOnSaturday = true,
                    hasClassOnSunday = false
                )
            )
        )
    }
}
