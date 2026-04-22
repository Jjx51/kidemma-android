package com.kidemma.common.utils

import android.content.Context
import android.content.res.Resources
import com.google.common.truth.Truth.assertThat
import com.kidemma.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

/*
 * File: AgeFormatterTest.kt
 * Description: Unit tests for AgeFormatter utility.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 22/04/26
 */
class AgeFormatterTest {

    private val context = mockk<Context>()
    private val resources = mockk<Resources>()

    @Before
    fun setUp() {
        every { context.resources } returns resources
    }

    @Test
    fun `when months is 0, then return only months label with 0`() {
        // GIVEN
        val months = 0
        every { resources.getQuantityString(R.plurals.kids_age_months, 0, 0) } returns "0 months"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("0 months")
    }

    @Test
    fun `when months is less than 12, then return only months label`() {
        // GIVEN
        val months = 5
        every { resources.getQuantityString(R.plurals.kids_age_months, 5, 5) } returns "5 months"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("5 months")
    }

    @Test
    fun `when months is exactly 12, then return only years label`() {
        // GIVEN
        val months = 12
        every { resources.getQuantityString(R.plurals.kids_age_years, 1, 1) } returns "1 year"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("1 year")
    }

    @Test
    fun `when months is multiple of 12, then return only years label`() {
        // GIVEN
        val months = 36
        every { resources.getQuantityString(R.plurals.kids_age_years, 3, 3) } returns "3 years"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("3 years")
    }

    @Test
    fun `when months is more than 12 and not multiple, then return years and months label`() {
        // GIVEN
        val months = 14
        every { resources.getQuantityString(R.plurals.kids_age_years, 1, 1) } returns "1 year"
        every { resources.getQuantityString(R.plurals.kids_age_months, 2, 2) } returns "2 months"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("1 year 2 months")
    }

    @Test
    fun `when months is many years and some months, then return years and months label`() {
        // GIVEN
        val months = 45
        every { resources.getQuantityString(R.plurals.kids_age_years, 3, 3) } returns "3 years"
        every { resources.getQuantityString(R.plurals.kids_age_months, 9, 9) } returns "9 months"

        // WHEN
        val result = formatAgeMonths(context, months)

        // THEN
        assertThat(result).isEqualTo("3 years 9 months")
    }
}
