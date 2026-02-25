package com.kidemma.introduction.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.kidemma.introduction.onboarding.presentation.OnboardingContent
import com.kidemma.introduction.onboarding.presentation.OnboardingContentProvider
import com.kidemma.introduction.onboarding.presentation.OnboardingContract
import org.junit.Rule
import org.junit.Test

/*
 * File: OnboardingScreenTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class OnboardingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun getString(id: Int): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.getString(id)
    }

    @Test
    fun givenInitialState_whenScreenIsDisplayed_thenFirstPageContentIsShown() {
        // --- GIVEN ---
        val pages = OnboardingContentProvider.getOnboardingPages()
        val pageUnderTest = pages.first()

        val initialState = OnboardingContract.State(
            currentPage = pageUnderTest,
            currentPageIndex = 0,
            totalPages = pages.size
        )

        composeTestRule.setContent {
            OnboardingContent(
                state = initialState,
                onNextClicked = { },
                onSkipClicked = { }
            )
        }

        // --- THEN ---
        val expectedTitle = getString(pageUnderTest.titleText)
        val expectedBody = getString(pageUnderTest.bodyText)
        val expectedButtonText = getString(pageUnderTest.buttonText)

        composeTestRule
            .onNodeWithText(expectedTitle)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(expectedBody)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(expectedButtonText)
            .assertIsDisplayed()
    }

    @Test
    fun whenNextButtonIsClicked_thenOnNextClickedCallbackIsInvoked() {
        // --- GIVEN ---
        val pages = OnboardingContentProvider.getOnboardingPages()
        val pageUnderTest = pages.first()

        val initialState = OnboardingContract.State(
            currentPage = pageUnderTest,
            currentPageIndex = 0,
            totalPages = pages.size
        )

        var wasNextClicked = false

        composeTestRule.setContent {
            OnboardingContent(
                state = initialState,
                onNextClicked = { wasNextClicked = true },
                onSkipClicked = { }
            )
        }

        // --- WHEN ---
        val buttonText = getString(pageUnderTest.buttonText)

        composeTestRule
            .onNodeWithText(buttonText)
            .performClick()

        // --- THEN ---
        assert(wasNextClicked)
    }
}