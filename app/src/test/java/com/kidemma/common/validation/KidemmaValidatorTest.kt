package com.kidemma.common.validation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class KidemmaValidatorTest {

    private companion object {
        const val BLANK = ""
        const val INVALID_EMAIL = "not-an-email"
        const val VALID_EMAIL = "test@example.com"
    }

    @Test
    fun `returns Required when value is blank and required`() {
        val result = KidemmaValidator.validate(
            value = BLANK,
            rules = listOf(KidemmaValidationRule.Required)
        )

        assertEquals(KidemmaValidationError.Required, result)
    }

    @Test
    fun `returns null when value is blank and optional`() {
        val result = KidemmaValidator.validate(
            value = BLANK,
            rules = listOf(KidemmaValidationRule.Optional)
        )

        assertNull(result)
    }

    @Test
    fun `returns InvalidEmail for invalid email`() {
        val result = KidemmaValidator.validate(
            value = INVALID_EMAIL,
            rules = listOf(KidemmaValidationRule.EmailFormat)
        )

        assertEquals(KidemmaValidationError.InvalidEmail, result)
    }

    @Test
    fun `returns null for valid email`() {
        val result = KidemmaValidator.validate(
            value = VALID_EMAIL,
            rules = listOf(KidemmaValidationRule.EmailFormat)
        )

        assertNull(result)
    }

    @Test
    fun `returns MinLength when value is less than min length`() {
        val minLength = 5

        val result = KidemmaValidator.validate(
            value = "1234",
            rules = listOf(KidemmaValidationRule.MinLength(minLength))
        )

        assertEquals(KidemmaValidationError.MinLength(minLength), result)
    }

    @Test
    fun `returns null when value meets min length`() {
        val minLength = 5

        val result = KidemmaValidator.validate(
            value = "12345",
            rules = listOf(KidemmaValidationRule.MinLength(minLength))
        )

        assertNull(result)
    }

    @Test
    fun `returns MaxLength when value exceeds max length`() {
        val maxLength = 5

        val result = KidemmaValidator.validate(
            value = "123456",
            rules = listOf(KidemmaValidationRule.MaxLength(maxLength))
        )

        assertEquals(KidemmaValidationError.MaxLength(maxLength), result)
    }

    @Test
    fun `returns null when value meets max length`() {
        val maxLength = 5

        val result = KidemmaValidator.validate(
            value = "12345",
            rules = listOf(KidemmaValidationRule.MaxLength(maxLength))
        )

        assertNull(result)
    }

    @Test
    fun `returns NoWhitespace when value contains whitespace`() {
        val result = KidemmaValidator.validate(
            value = "test test",
            rules = listOf(KidemmaValidationRule.NoWhitespace)
        )

        assertEquals(KidemmaValidationError.NoWhitespace, result)
    }

    @Test
    fun `returns null when value contains no whitespace`() {
        val result = KidemmaValidator.validate(
            value = "testtest",
            rules = listOf(KidemmaValidationRule.NoWhitespace)
        )

        assertNull(result)
    }

    @Test
    fun `returns first error when multiple rules fail`() {
        val result = KidemmaValidator.validate(
            value = BLANK,
            rules = listOf(
                KidemmaValidationRule.Required,
                KidemmaValidationRule.EmailFormat
            )
        )

        assertEquals(KidemmaValidationError.Required, result)
    }

    @Test
    fun `returns null when all rules pass`() {
        val result = KidemmaValidator.validate(
            value = "user@example.com",
            rules = listOf(
                KidemmaValidationRule.Required,
                KidemmaValidationRule.EmailFormat,
                KidemmaValidationRule.MinLength(10)
            )
        )

        assertNull(result)
    }
}