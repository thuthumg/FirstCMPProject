package org.example.firstcmpproject

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.runComposeUiTest
import org.example.firstcmpproject.core.utils.CATEGORY_SECTION
import org.example.firstcmpproject.core.utils.EMAIL_OR_PHONE_TEXT_FIELD
import org.example.firstcmpproject.core.utils.FEATURE_MOVIE_IMAGE
import org.example.firstcmpproject.core.utils.GENRE_NAME
import org.example.firstcmpproject.core.utils.MOVIE_DETAIL_IMAGE
import org.example.firstcmpproject.core.utils.MOVIE_DETAIL_LAZY_COLUMN
import org.example.firstcmpproject.core.utils.MOVIE_NAME
import org.example.firstcmpproject.core.utils.SIGN_IN_BUTTON
import org.example.firstcmpproject.core.utils.SIMILAR_MOVIE_GRID
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


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun fromLoginScreen_navigateToMovieDetailScreen() = runComposeUiTest {
        setContent { App() }

        // 1) Login -> go to movie list
        onNodeWithTag(SIGN_IN_BUTTON)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        // Wait featured movie appears
        waitUntil(timeoutMillis = 70000) {
            onNodeWithTag(FEATURE_MOVIE_IMAGE).isDisplayed()
        }

        // 2) Tap featured movie -> navigate to details
        onNodeWithTag(FEATURE_MOVIE_IMAGE)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        // Wait details screen
        waitUntil(timeoutMillis = 70000) {
            onNodeWithTag(MOVIE_DETAIL_IMAGE).isDisplayed()
            onNodeWithTag(MOVIE_NAME).isDisplayed()
        }

        //Data Point 1: Detail Image
        onNodeWithTag(MOVIE_DETAIL_IMAGE)
            .assertExists()
            .assertIsDisplayed()

        //Data Point 2: Movie Name
        onNodeWithTag(MOVIE_NAME)
            .assertExists()
            .assertIsDisplayed()

        // 3) Scroll to Similar Movie Grid (Data Point 3)
        onNodeWithTag(MOVIE_DETAIL_LAZY_COLUMN)
            .assertExists()
            .performScrollToNode(hasTestTag(SIMILAR_MOVIE_GRID))

        //Data Point 3: Similar movie grid
        onNodeWithTag(SIMILAR_MOVIE_GRID)
            .assertExists()
            .assertIsDisplayed()


        val detailId = 83533
        onNodeWithTag("SIMILAR_MOVIE_ITEM_$detailId")
            .assertDoesNotExist()
    }


}