package org.example.firstcmpproject

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import org.example.firstcmpproject.core.utils.CATEGORY_SECTION
import org.example.firstcmpproject.core.utils.EMAIL_OR_PHONE_TEXT_FIELD
import org.example.firstcmpproject.core.utils.GENRE_NAME
import org.example.firstcmpproject.core.utils.SIGN_IN_BUTTON
import kotlin.test.Test

class UITest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginScreen_emailTextFieldExists() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag(EMAIL_OR_PHONE_TEXT_FIELD).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun fromLoginScreen_navigateToHomeScreen_categorySectionExists() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag(SIGN_IN_BUTTON).performClick()
        waitUntil (timeoutMillis = 10000){
            onNodeWithTag(CATEGORY_SECTION).isDisplayed()
        }

        onNodeWithTag(CATEGORY_SECTION).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun fromLoginScreen_navigateToHomeScreen_genreNameExists() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag(SIGN_IN_BUTTON).performClick()
        waitUntil (timeoutMillis = 10000){
            onNodeWithTag("${GENRE_NAME}-Action").isDisplayed()
        }

        onNodeWithTag("${GENRE_NAME}-Action").assertExists()
    }
}