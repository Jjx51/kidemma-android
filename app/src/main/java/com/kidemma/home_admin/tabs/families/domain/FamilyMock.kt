package com.kidemma.home_admin.tabs.families.domain

import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyMemberUiModel
import com.kidemma.authentication.domain.model.FamilyUiModel

object FamilyMock {
    val familyList = listOf(
        FamilyUiModel(
            id = 1,
            nickname = "Los Garcia",
            familyName = "García",
            members = listOf(
                FamilyMemberUiModel(
                    1,
                    "Juan",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    2,
                    "María",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    3,
                    "Luis",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 2,
            nickname = "Familia Lopez",
            familyName = "López",
            members = listOf(
                FamilyMemberUiModel(
                    4,
                    "Carlos",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    5,
                    "Ana",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 3,
            nickname = "Los Martinez",
            familyName = "Martínez",
            members = listOf(
                FamilyMemberUiModel(
                    6,
                    "Pedro",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    7,
                    "Laura",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    8,
                    "Sofía",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    9,
                    "Diego",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    9,
                    "Diego",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 4,
            nickname = "Team Hernandez",
            familyName = "Hernández",
            members = listOf(
                FamilyMemberUiModel(
                    10,
                    "Miguel",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 5,
            nickname = "Los Torres",
            familyName = "Torres",
            members = listOf(
                FamilyMemberUiModel(
                    11,
                    "Fernando",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    12,
                    "Patricia",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 5,
            nickname = "Los Perez",
            familyName = "Perez",
            members = listOf(
                FamilyMemberUiModel(
                    11,
                    "Fernando",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    12,
                    "Patricia",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 5,
            nickname = "Gonzalez Team",
            familyName = "Gonzalez",
            members = listOf(
                FamilyMemberUiModel(
                    11,
                    "Fernando",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    12,
                    "Patricia",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                )
            )
        ),
        FamilyUiModel(
            id = 5,
            nickname = "Garza Team",
            familyName = "Garza",
            members = listOf(
                FamilyMemberUiModel(
                    11,
                    "Fernando",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    12,
                    "Patricia",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    13,
                    "Valeria",
                    R.drawable.img_boy
                )
            )
        )

    )
}