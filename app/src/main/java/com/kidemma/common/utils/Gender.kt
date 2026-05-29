package com.kidemma.common.utils

import androidx.annotation.DrawableRes
import com.kidemma.R

enum class Gender {
    MALE, FEMALE, OTHER
}

@get:DrawableRes
val Gender.defaultAvatarResId: Int
    get() = when (this) {
        Gender.FEMALE -> R.drawable.img_girl
        else -> R.drawable.img_boy
    }
