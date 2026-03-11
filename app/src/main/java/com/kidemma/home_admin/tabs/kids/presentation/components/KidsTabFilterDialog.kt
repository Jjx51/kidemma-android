package com.kidemma.home_admin.tabs.kids.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.kidemma.R
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaHeadlineSmall
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTertiaryButton
import com.kidemma.common.components.VerticalSpacerMedium
import com.kidemma.common.components.VerticalSpacerSmall
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.utils.Gender
import com.kidemma.home_admin.tabs.kids.domain.models.KidsTabFilterResult
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Dimens.ButtonsSpacing
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Dimens.DialogMaxWidth
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Dimens.DialogPadding
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Numbers.DIALOG_WIDTH_FRACTION
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Numbers.MAX_AGE_MONTHS
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialogUiConstants.Numbers.MIN_AGE_MONTHS
import java.time.LocalDate

/*
 * File: KidsTabFilterDialog.kt
 * Description: Kids tab filter dialog composable.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 11/03/26
 */

@Composable
internal fun KidsTabFilterDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onApplyFilter: (KidsTabFilterResult) -> Unit,
) {
    var selectedGender: Gender? by remember { mutableStateOf(null) }

    // Age range in months (inclusive).
    var minAgeMonths by remember { mutableIntStateOf(MIN_AGE_MONTHS) }
    var maxAgeMonths by remember { mutableIntStateOf(MAX_AGE_MONTHS) }

    Dialog(onDismissRequest = onDismiss) {
        KidemmaCard(
            modifier = modifier
                .widthIn(max = DialogMaxWidth)
                .fillMaxWidth(DIALOG_WIDTH_FRACTION),
        ) {
            Column(modifier = Modifier.padding(DialogPadding)) {
                KidemmaHeadlineSmall(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.kids_filter_dialog_title),
                    textAlign = TextAlign.Center,
                )

                VerticalSpacerMedium()

                GenderFilterSection(
                    selectedGender = selectedGender,
                    onGenderSelected = { selectedGender = it },
                )

                VerticalSpacerMedium()

                AgeRangeFilterSection(
                    minAgeMonths = minAgeMonths,
                    maxAgeMonths = maxAgeMonths,
                    onMinAgeChanged = { newMin ->
                        minAgeMonths = newMin.coerceAtMost(maxAgeMonths)
                    },
                    onMaxAgeChanged = { newMax ->
                        maxAgeMonths = newMax.coerceAtLeast(minAgeMonths)
                    },
                )

                VerticalSpacerMedium()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(ButtonsSpacing),
                ) {
                    KidemmaTertiaryButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.kids_filter_dialog_clear),
                        onClick = {
                            selectedGender = null
                            minAgeMonths = MIN_AGE_MONTHS
                            maxAgeMonths = MAX_AGE_MONTHS
                        },
                    )

                    KidemmaPrimaryButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.kids_filter_dialog_apply),
                        onClick = {
                            val today = LocalDate.now()

                            val minBirthday = today.minusMonths(maxAgeMonths.toLong())
                            val maxBirthday = today.minusMonths(minAgeMonths.toLong())

                            onApplyFilter(
                                KidsTabFilterResult(
                                    minBirthday = minBirthday,
                                    maxBirthday = maxBirthday,
                                    gender = selectedGender,
                                ),
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun GenderFilterSection(
    modifier: Modifier = Modifier,
    selectedGender: Gender?,
    onGenderSelected: (Gender?) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        KidemmaLabelLarge(text = stringResource(R.string.kids_filter_gender_section_title))

        val genderOptions = listOf(
            GenderOption(Gender.MALE, R.string.kids_filter_gender_male),
            GenderOption(Gender.FEMALE, R.string.kids_filter_gender_female),
            GenderOption(null, R.string.kids_filter_gender_both),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            genderOptions.forEach { option ->
                val label = stringResource(option.labelRes)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedGender == option.gender,
                            onClick = { onGenderSelected(option.gender) },
                        )
                        .semantics { contentDescription = label },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedGender == option.gender,
                        onClick = { onGenderSelected(option.gender) },
                    )
                    KidemmaBodyMedium(text = label)
                }
            }
        }
    }
}

private data class GenderOption(
    val gender: Gender?,
    @param:StringRes val labelRes: Int,
)

@Composable
private fun AgeRangeFilterSection(
    modifier: Modifier = Modifier,
    minAgeMonths: Int,
    maxAgeMonths: Int,
    onMinAgeChanged: (Int) -> Unit,
    onMaxAgeChanged: (Int) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        KidemmaLabelLarge(text = stringResource(R.string.kids_filter_age_range_section_title))
        VerticalSpacerSmall()

        val minAgeLabel = formatAgeMonths(minAgeMonths)
        val maxAgeLabel = formatAgeMonths(maxAgeMonths)

        KidemmaBodyMedium(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                R.string.kids_filter_age_range_value,
                minAgeLabel,
                maxAgeLabel,
            ),
            textAlign = TextAlign.Center,
        )

        VerticalSpacerMedium()

        KidemmaBodyMedium(
            text = stringResource(R.string.kids_filter_age_range_min_label),
            modifier = Modifier.fillMaxWidth(),
        )
        Slider(
            value = minAgeMonths.toFloat(),
            onValueChange = { newValue ->
                val newMin = newValue.toInt().coerceAtMost(maxAgeMonths)
                onMinAgeChanged(newMin)
            },
            valueRange = MIN_AGE_MONTHS.toFloat()..maxAgeMonths.toFloat(),
            steps = ((maxAgeMonths - MIN_AGE_MONTHS) - 1).coerceAtLeast(0),
        )

        VerticalSpacerSmall()

        KidemmaBodyMedium(
            text = stringResource(R.string.kids_filter_age_range_max_label),
            modifier = Modifier.fillMaxWidth(),
        )
        Slider(
            value = maxAgeMonths.toFloat(),
            onValueChange = { newValue ->
                val newMax = newValue.toInt().coerceAtLeast(minAgeMonths)
                onMaxAgeChanged(newMax)
            },
            valueRange = minAgeMonths.toFloat()..MAX_AGE_MONTHS.toFloat(),
            steps = ((MAX_AGE_MONTHS - minAgeMonths) - 1).coerceAtLeast(0),
        )
    }
}

@Composable
private fun formatAgeMonths(months: Int): String {
    val years = months / 12
    val remainingMonths = months % 12

    return when {
        years == 0 -> pluralStringResource(R.plurals.kids_age_months, remainingMonths, remainingMonths)
        remainingMonths == 0 -> pluralStringResource(R.plurals.kids_age_years, years, years)
        else -> {
            val yearsLabel = pluralStringResource(R.plurals.kids_age_years, years, years)
            val monthsLabel = pluralStringResource(R.plurals.kids_age_months, remainingMonths, remainingMonths)
            "$yearsLabel $monthsLabel"
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
private fun KidsTabFilterDialogPreview() {
    KidemmaTheme {
        KidsTabFilterDialog(
            onDismiss = {},
            onApplyFilter = {},
        )
    }
}