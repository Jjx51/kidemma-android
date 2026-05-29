package com.kidemma.home_admin.tabs.families.domain

import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyMemberUiModel
import com.kidemma.authentication.domain.model.FamilyUiModel

/*
 * File: FamiliesTabMockProvider
 * Description: mock list for families
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 28/05/26
 */
object FamiliesTabMockProvider {
    val familyList = listOf(
        FamilyUiModel(
            id = 1,
            nickname = "Los Garcia",
            familyName = "García",
            members = listOf(
                FamilyMemberUiModel(
                    1,
                    name = "Juan",
                    gender = "masculino",
                    role = "padre",
                    image = R.drawable.img_father,
                    imageError = R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    2,
                    name = "María",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_mom,
                    imageError = R.drawable.img_mom
                ),
                FamilyMemberUiModel(
                    3,
                    name = "Luis",
                    gender = "masculino",
                    role = "hijo",
                    image = R.drawable.img_boy,
                    imageError = R.drawable.img_boy
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
                    name = "Carlos",
                    gender = "masculino",
                    role = "padre",
                    image = R.drawable.img_father,
                    imageError = R.drawable.img_father
                ),
                FamilyMemberUiModel(
                    5,
                    name = "Ana",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_mom,
                    imageError = R.drawable.img_mom
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
                    name = "Pedro",
                    gender = "masculino",
                    role = "padre",
                    image = R.drawable.img_father,
                    imageError = R.drawable.img_father
                ),
                FamilyMemberUiModel(
                    7,
                    name = "Laura",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_mom,
                    imageError = R.drawable.img_mom
                ),
                FamilyMemberUiModel(
                    8,
                    name = "Sofía",
                    gender = "femenino",
                    role = "hija",
                    image = R.drawable.img_girl,
                    imageError = R.drawable.img_girl
                ),
                FamilyMemberUiModel(
                    9,
                    name = "María",
                    gender = "femenino",
                    role = "hija",
                    image = R.drawable.img_girl,
                    imageError = R.drawable.img_girl
                ),
                FamilyMemberUiModel(
                    10,
                    name = "Diego",
                    gender = "masculino",
                    role = "hijo",
                    image = R.drawable.img_boy,
                    imageError = R.drawable.img_boy
                ),
                FamilyMemberUiModel(
                    11,
                    name = "Diego",
                    gender = "masculino",
                    role = "hijo",
                    image = R.drawable.img_boy,
                    imageError = R.drawable.img_boy
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
                    name = "Miguel",
                    gender = "masculino",
                    role = "",
                    image = R.drawable.img_boy,
                    imageError = R.drawable.img_father
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
                    name = "Fernando",
                    gender = "masculino",
                    role = "padre",
                    image = R.drawable.img_father,
                    imageError = R.drawable.img_father
                ),
                FamilyMemberUiModel(
                    12,
                    "Patricia",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_mom,
                    imageError = R.drawable.img_mom
                ),
                FamilyMemberUiModel(
                    13,
                    name = "Valeria",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_boy,
                    imageError = R.drawable.img_boy
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
                    name = "Fernando",
                    gender = "masculino",
                    role = "padre",
                    image = R.drawable.img_father,
                    imageError = R.drawable.img_father
                ),
                FamilyMemberUiModel(
                    12,
                    name = "Patricia",
                    gender = "femenino",
                    role = "madre",
                    image = R.drawable.img_mom,
                    imageError = R.drawable.img_mom
                ),
                FamilyMemberUiModel(
                    13,
                    name = "Valeria",
                    gender = "femenino",
                    role = "hija",
                    image = R.drawable.img_girl,
                    imageError = R.drawable.img_girl
                )
            )
        ),

    )
}